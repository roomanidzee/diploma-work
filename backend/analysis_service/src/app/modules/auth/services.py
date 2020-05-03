
import os

from app.config import init_config
from app.modules.common.cache import factory
from app.utils import get_config_type

class AuthService:
    """Class for user validation"""

    async def check_for_user(self, token: str) -> bool:
        cache = await factory(init_config(get_config_type()))
        await cache.contains(token)
