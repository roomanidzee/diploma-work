
from app.modules.common.db import factory
from app.modules.auth.services import AuthService
from app.config import init_config
from app.utils import get_config_type

class DBService:
    """Service for work with DB instances"""

    async def check_for_file(self, auth_token: str, file_id: str) -> bool:

        auth_service = AuthService()
        db = await factory(init_config(get_config_type()))
        
        is_token_exists = await auth_service.check_for_user(auth_token)

        return (is_token_exists) and (db.check_for_file(file_id) is not None)
