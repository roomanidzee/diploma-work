
import os
from enum import Enum
from typing import Union

import lya

from app.config.classes import (
    DevelopmentConfig,
    ProductionConfig,
    TestingConfig
)

CONFIG_INSTANCE_TYPE = Union[
    DevelopmentConfig,
    ProductionConfig,
    TestingConfig
]

class ConfigEnum(str, Enum):
    DEV = 'development'
    PROD = 'production'
    TEST = 'testing'

    @classmethod
    def list_values(cls):
        return list(map(lambda c: c.value, cls))

def init_config(launch_type: str) -> CONFIG_INSTANCE_TYPE:
    """
    Method for system config initialization. 
    Based on launch type and ANALYSIS_APP_CONFIG_PATH environment variable value/
    """

    config_path = os.getenv('ANALYSIS_APP_CONFIG_PATH')

    if launch_type not in ConfigEnum.list_values():
        raise ValueError(f"Wrong input launch type: {launch_type}")

    if (launch_type == ConfigEnum.TEST.value) and (not config_path):
        return TestingConfig()
    
    if not config_path:
        raise ValueError("No config path for app launch")

    if not os.path.exists(config_path):
        raise ValueError("No config file on input path")

    ext = os.path.splitext(config_path)[1]

    if ext != '.yml':
        raise ValueError("Wrong config file on input path")

    config = lya.AttrDict.from_yaml(config_path)

    if launch_type == ConfigEnum.DEV.value:
        return DevelopmentConfig(**config)
    elif launch_type == ConfigEnum.PROD.value:
        return ProductionConfig(**config)
