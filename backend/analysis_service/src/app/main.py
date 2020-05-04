import os
from pathlib import Path

from fastapi import FastAPI, APIRouter

from app.config import init_config
from app.config.classes import (
    DevelopmentConfig,
    ProductionConfig
)

from app.modules.auth import routes as auth_routes
from app.modules.simple import routes as status_routes
from app.modules.analyse import routes as analyse_routes

from app.modules.common.db.utils import (
    init_db_conn,
    close_db_conn
)

from app.settings import logging

app = FastAPI()
base_path = Path(__file__).parent.parent.parent

def register_endpoints(app: FastAPI):

    router = APIRouter()

    router.include_router(
        status_routes.router,
        prefix='/status',
        tags=['status'],
    )
    router.include_router(
        auth_routes.router,
        prefix='/auth',
        tags=['auth']
    )
    router.include_router(
        analyse_routes.router,
        prefix='/analyse',
        tags=['analyse']
    )

    app.include_router(
        router,
        prefix='/api',
        tags=['api']
    )

def register_extensions(app: FastAPI):
    logging.configure(base_path)

def create_app(launch_type: str):
    
    config_output = init_config(launch_type)

    app = FastAPI(title=config_output.title, version=config_output.version)

    if isinstance(config_output, (DevelopmentConfig, ProductionConfig)):
        app.add_event_handler('startup', init_db_conn)
        app.add_event_handler('shutdown', close_db_conn)

    register_endpoints(app)
    register_extensions(app)

    return app
