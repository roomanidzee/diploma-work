import os

from fastapi import FastAPI

from app.config import init_config
from app.modules.simple import routes as status_routes

app = FastAPI()

def register_endpoints(app: FastAPI):
    app.include_router(
        status_routes.router,
        prefix='/status',
        tags=['status'],
    )

def create_app(launch_type: str):
    
    # TODO: initialization of di by config
    config_output = init_config(launch_type)

    app = FastAPI(title=config_output.title, version=config_output.version)

    register_endpoints(app)

    return app
