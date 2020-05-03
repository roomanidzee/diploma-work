
import asyncio
import functools

def run_in_executor(f):
    """Decorator for wrapping blocking function to asyncio"""

    @functools.wraps(f)
    def inner(*args, **kwargs):
        loop = asyncio.get_running_loop()
        return loop.run_in_executor(None, lambda: f(*args, **kwargs))

    return inner

class AsyncObject(object):
    """Utility class for making async objects."""

    async def __new__(cls, *a, **kw):
        instance = super().__new__(cls)
        await instance.__init__(*a, **kw)
        return instance

    async def __init__(self):
        pass
