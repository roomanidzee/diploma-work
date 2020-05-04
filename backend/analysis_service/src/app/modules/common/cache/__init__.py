
from app.config.classes import (
    DevelopmentConfig,
    ProductionConfig
)
from app.modules.common.cache.classes import (
    RedisCache,
    MemoryCache
)

async def factory(config_class):

    if isinstance(config_class, (DevelopmentConfig, ProductionConfig)):
        return await RedisCache(config_class)
    else:
        return await MemoryCache()
