import os
import uvicorn

from app.config import init_config
from app.main import create_app
from app.utils import get_config_type

app = create_app(get_config_type())
config_output = init_config(get_config_type())

if __name__ == '__main__':
    uvicorn.run(app, host=config_output.host, port=config_output.port)