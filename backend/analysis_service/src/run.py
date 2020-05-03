import os
import uvicorn

from app.config import init_config, create_app

app = create_app(os.getenv('FASTAPI_ENV'))
config_output = init_config(os.getenv('FASTAPI_ENV'))

if __name__ == '__main__':
    uvicorn.run(app, host=config_output.host, port=config_output.port)