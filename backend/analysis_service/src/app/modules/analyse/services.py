
from app.modules.common.db import factory
from app.modules.common.files import factory as files_factory
from app.modules.auth.services import AuthService
from app.config import init_config
from app.utils import get_config_type

class DBService:
    """Service for work with DB instances"""

    async def check_for_file(self, auth_token: str, file_id: str) -> bool:

        auth_service = AuthService()
        db = await factory(init_config(get_config_type()))
        files = await files_factory(init_config(get_config_type()))
        
        is_token_exists = await auth_service.check_for_user(auth_token)

        if not is_token_exists:
            return False

        db_model = await db.find_by_id(file_id)

        if not db_model:
            return False

        return files.check_path(db_model['path'])
