import os
from pathlib import Path

from fastapi import FastAPI

from app.config import init_config
from app.modules.simple import routes as status_routes
from app.settings import logging

app = FastAPI()
base_path = Path(__file__).parent.parent

def register_endpoints(app: FastAPI):
    app.include_router(
        status_routes.router,
        prefix='/status',
        tags=['status'],
    )

def register_extensions(app: FastAPI):
    logging.configure(base_path)

def create_app(launch_type: str):
    
    # TODO: initialization of di by config
    config_output = init_config(launch_type)

    app = FastAPI(title=config_output.title, version=config_output.version)

    register_endpoints(app)

    return app
