FROM python:3.7-slim-buster as development_build

LABEL maintainer = "Andrey Romanov"

ARG DOCKERIZE_VERSION
ARG POETRY_VERSION

ENV DOCKERIZE_VERSION=${DOCKERIZE_VERSION} \
    POETRY_VERSION=${POETRY_VERSION} \
    PYTHONFAULTHANDLER=1 \
    PYTHONUNBUFFERED=1 \
    PYTHONHASHSEED=random \
    PIP_NO_CACHE_DIR=off \
    PIP_DISABLE_PIP_VERSION_CHECK=on \
    PIP_DEFAULT_TIMEOUT=100

RUN apt update  \
    && apt install -y --no-install-recommends gettext libpq-dev netcat g++ gcc wget python3-dev \
    && apt-get clean && rm -rf /var/cache/apt/archives && rm -rf /var/lib/apt/lists/*

RUN pip install "poetry==$POETRY_VERSION" \
    && wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
    && tar -C /usr/local/bin -xzvf "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
    && rm "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz"

WORKDIR /pysetup
COPY ./poetry.lock ./pyproject.toml /pysetup/

COPY ./docker/entrypoint.sh /docker-entrypoint.sh

RUN poetry config virtualenvs.create false \
    && poetry install

WORKDIR /code

ENTRYPOINT ["/docker-entrypoint.sh"]
