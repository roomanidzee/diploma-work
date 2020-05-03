from dataclasses import dataclass

@dataclass
class Config:
    host: str
    port: str
    title: str
    version: str
    is_debug: bool

@dataclass
class DataSourceConfig:
    redis_url: str
    mongo_url: str
    hdfs_url: str

@dataclass
class DevelopmentConfig(Config, DataSourceConfig):
    is_debug: bool = True
    
@dataclass
class ProductionConfig(Config, DataSourceConfig):
    is_debug: bool = False

@dataclass
class TestingConfig(Config):
    host: str = ''
    port: str = ''
    is_debug: bool = True
    title: str = 'testing'
    version: str = '0.0.0'
