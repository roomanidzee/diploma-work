# analysis-service

## Launch

```
cd src
export FASTAPI_ENV=development (or export FASTAPI_ENV=production)
export ANALYSIS_APP_CONFIG_PATH=config.yml
pip install poetry
poetry install
python run.py
```

## Tests
```
cd src
export FASTAPI_ENV=testing
pytest tests
```