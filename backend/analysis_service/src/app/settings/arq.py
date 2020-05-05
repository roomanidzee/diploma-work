
from arq.connections import RedisSettings
from pydantic.utils import import_string

from app.config.classes import (
    DevelopmentConfig,
    ProductionConfig
)

from app.config import init_config
from app.utils import get_config_type

config = init_config(get_config_type())

"""
if not isinstance(config, (DevelopmentConfig, ProductionConfig)):
    raise ValueError("Launch of 'arq' not supported in tests mode")
"""

settings = RedisSettings(
    host = config.redis.host,
    port = int(config.redis.port)
)

tasks = [
    "app.modules.anaylse.tasks.build_model",
    "app.modules.common.tasks.upload_models"
]

class WorkerSettings:
    redis_settings = settings
    functions = [
        import_string(elem)
        for elem in tasks
    ]
