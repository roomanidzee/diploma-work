from dataclasses import dataclass

@dataclass
class Config:
    host: str
    port: str
    title: str
    version: str

@dataclass
class MongoDBConfig:
    host: str
    port: str
    user: str
    password: str
    db: str

    def get_url(self):
        return "mongodb://{0}:{1}@{2}:{3}/{4}".format(
            self.user,
            self.password,
            self.host,
            self.port,
            self.db
        )

@dataclass
class RedisConfig:
    host: str
    port: str

    def get_url(self):
        return "redis://{0}:{1}".format(
            self.host,
            self.port
        )

@dataclass
class DataSourceConfig(Config):
    redis: RedisConfig
    mongo: MongoDBConfig
    hdfs_url: str
    is_debug: bool

@dataclass
class DevelopmentConfig(DataSourceConfig):
    is_debug: bool = True
    
@dataclass
class ProductionConfig(DataSourceConfig):
    is_debug: bool = False

@dataclass
class TestingConfig(Config):
    host: str = ''
    port: str = ''
    is_debug: bool = True
    title: str = 'testing'
    version: str = '0.0.0'
