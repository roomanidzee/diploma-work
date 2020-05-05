
import os
from pathlib import Path

base_path = Path(__file__).parent.parent.parent

def get_config_type():
    return os.getenv('FASTAPI_ENV')
