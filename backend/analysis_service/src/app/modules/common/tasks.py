
from app.config import init_config
from app.utils import get_config_type

from app.modules.analyse.tasks import get_models_path
from app.modules.common import files

async def upload_models(ctx: dict):

    models_path = get_models_path()

    model_files = [
        elem
        for elem in models_path.iterdir()
        if elem.is_file()
    ]

    files = await files.factory(init_config(get_config_type()))

    for elem in model_files:
        await files.create_file(elem.name)
        await files.append_to_file(elem.name, elem.read_text())
    
    return None

