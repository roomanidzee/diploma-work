
from app.config.classes import (
    DevelopmentConfig,
    ProductionConfig,
    TestingConfig
)

from app.modules.common.db.classes import(
    MongoDBIndexer,
    MemoryDBIndexer
)

async def factory(config_class):

    if isinstance(config_class, (DevelopmentConfig, ProductionConfig)):
        return await MongoDBIndexer()
    else:
        return await MemoryDBIndexer()
