import os
from pathlib import Path

from fastapi import FastAPI

from app.config import init_config
from app.modules.simple import routes as status_routes
from app.modules.auth import routes as auth_routes
from app.settings import logging

app = FastAPI()
base_path = Path(__file__).parent.parent

def register_endpoints(app: FastAPI):
    app.include_router(
        status_routes.router,
        prefix='/status',
        tags=['status'],
    )
    app.include_router(
        auth_routes.router,
        prefix='/auth',
        tags=['auth']
    )

def register_extensions(app: FastAPI):
    logging.configure(base_path)

def create_app(launch_type: str):
    
    config_output = init_config(launch_type)
    app = FastAPI(title=config_output.title, version=config_output.version)

    register_endpoints(app)

    return app
