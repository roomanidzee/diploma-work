
from app.config import init_config
from app.main import create_app
from app.utils import get_config_type

app = create_app(get_config_type())
config_output = init_config(get_config_type())