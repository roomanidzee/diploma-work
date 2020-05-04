
from abc import abstractmethod
from typing import Protocol, Union, Dict

from app.config import init_config
from app.utils import get_config_type
from app.modules.common import AsyncObject, run_in_executor
from app.modules.common.db.utils import get_db_client

MODEL_TYPE = Dict[str, Union[str, int]]

class DBIndexer(Protocol):
    """Abstract class for working with DB"""

    @abstractmethod
    async def find_by_id(self, id: str) -> MODEL_TYPE:
        raise NotImplementedError("Method is not implemented yet")

    @abstractmethod
    async def save(self, input: MODEL_TYPE) -> None:
        raise NotImplementedError("Method is not implemented yet")

class MongoDBIndexer(DBIndexer, AsyncObject):
    """DB work by MongoDB"""

    async def __init__(self):
        super().__init__()
        self.db_client = await get_db_client()
        config = init_config(get_config_type).mongo
        self.db_name = config.db
        self.collection_name = "files"

    async def find_by_id(self, id: str) -> MODEL_TYPE:
        return await self.db_client.find_one({"_id": id})

class MemoryDBIndexer(DBIndexer, AsyncObject):
    """DB work by memory impl"""

    data_list = [{}]

    async def __init__(self):
        super().__init__()

    @run_in_executor
    def check_by_id(self, id: str) -> MODEL_TYPE:
        return next(
            (elem for elem in self.data_list if elem['_id'] == id),
            None
        )
    
    async def find_by_id(self, id: str) -> MODEL_TYPE:
        return await self.check_by_id(id)

    @run_in_executor
    def save_elem(self, input_elem: MODEL_TYPE) -> None:
        return self.data_list.append(input_elem)
    
    async def save(self, input_elem: MODEL_TYPE) -> None:
        return await self.save_elem(input_elem)
