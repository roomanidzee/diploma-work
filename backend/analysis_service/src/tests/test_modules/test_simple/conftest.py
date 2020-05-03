import pytest
from starlette.testclient import TestClient

from app.main import create_app


@pytest.fixture(scope="module")
def test_app():

    app = create_app('testing')
    client = TestClient(app)
    yield client
