
import pytest

from pathlib import PurePath, Path

@pytest.fixture(scope="module")
def config_path():
    yield PurePath(__file__).parent.parent / Path('resources') / Path('config.yml')
