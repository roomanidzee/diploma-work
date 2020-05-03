import os

def get_config_type():
    return os.getenv('FASTAPI_ENV')
