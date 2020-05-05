
from app.config.classes import (
    DevelopmentConfig,
    ProductionConfig
)

from app.utils import base_path

from app.modules.common.files.classes import HDFSFilesWork, MemoryFilesWork

async def factory(config_class):

    if isinstance(config_class, (DevelopmentConfig, ProductionConfig)):
        return await HDFSFilesWork(config_class)
    else:
        return await MemoryFilesWork(base_path)
