import pytest
from starlette.testclient import TestClient

from app.main import create_app

@pytest.fixture(scope="module")
def test_app():
    return create_app('testing')

@pytest.fixture(scope="module")
def test_client(test_app):
    return TestClient(test_app)
