import pytest

from pathlib import PurePath, Path

from app.config import init_config
from app.config.classes import (
    DevelopmentConfig,
    TestingConfig
)

def test_success_config_load(monkeypatch, config_path):

    monkeypatch.setenv('ANALYSIS_APP_CONFIG_PATH', str(config_path))

    config_output = init_config('development')

    assert isinstance(config_output, DevelopmentConfig)

def test_for_testing_config_load():

    config_output = init_config('testing')

    assert isinstance(config_output, TestingConfig)
