
from motor.motor_asyncio import AsyncIOMotorClient

from app.config import init_config
from app.utils import get_config_type


class DBInstance:
    client: AsyncIOMotorClient = None

db_instance = DBInstance()

async def get_db_client() -> AsyncIOMotorClient:
    return db_instance.client

async def init_db_conn():
    config = init_config(get_config_type).mongo

    url = "mongodb://{0}:{1}@{2}:{3}/{4}".format(
        config.user,
        config.password,
        config.host,
        config.port,
        config.db
    )

    db_instance.client = AsyncIOMotorClient(str(url))

async def close_db_conn():
    db_instance.client.close()