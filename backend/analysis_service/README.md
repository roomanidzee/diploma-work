# analysis-service

## Install

```
cd src
pip install poetry
poetry install
```

## Launch (app)

```
export FASTAPI_ENV=development (or export FASTAPI_ENV=production)
export ANALYSIS_APP_CONFIG_PATH=config.yml
python run.py
```

## Launch (periodic tasks)

```
arq app.settings.arq.WorkerSettings
```

## Tests

```
export FASTAPI_ENV=testing
pytest tests
```