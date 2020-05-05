
from abc import abstractmethod

import aioredis

from app.modules.common import AsyncObject, run_in_executor
from app.config.classes import DataSourceConfig

class Cache:
    """Abstract class for working with cache."""

    @abstractmethod
    async def contains(self, cache_key: str) -> bool:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def get(self, cache_key: str) -> str:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def set(self, cache_key: str, cache_value: str) -> None:
        raise NotImplementedError("Method is not implemented yet")

class RedisCache(Cache, AsyncObject):
    """Cache implementation with Redis"""

    async def __init__(self, config: DataSourceConfig):
        super().__init__()
        redis_url = config.redis.get_url()
        self.redis_pool = await aioredis.create_connection(config.redis_url)

    async def contains(self, cache_key: str) -> bool:
        return await self.redis_pool.exists(cache_key)

    async def get(self, cache_key: str) -> str:
        return await self.redis_pool.get(cache_key)

    async def set(self, cache_key: str, cache_value: str) -> None:
        await self.redis_pool.set(cache_key, cache_value)

class MemoryCache(Cache, AsyncObject):
    """Cache implementation with Memory"""

    data_dict = {}

    async def __init__(self):
        super().__init__()

    @run_in_executor
    def check_key(self, cache_key: str) -> bool:
        return cache_key in self.data_dict

    async def contains(self, cache_key: str) -> bool:
        return await self.check_key(cache_key)

    @run_in_executor
    def memory_get(self, cache_key: str) -> str:
        return self.data_dict.get(cache_key, None)

    async def get(self, cache_key: str) -> str:
        return await self.memory_get(cache_key)

    @run_in_executor
    def memory_set(self, cache_key: str, cache_value: str) -> None:
        self.data_dict[cache_key] = cache_value

    async def set(self, cache_key: str, cache_value: str) -> None:
        await self.memory_set(cache_key, cache_value)
