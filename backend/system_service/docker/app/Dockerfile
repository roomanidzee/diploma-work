FROM gradle:6.0.1-jdk11 as build

LABEL maintainer = 'Andrey Romanov'

COPY --chown=gradle:gradle . /code
COPY --chown=gradle:gradle ./docker/app/entrypoint.sh /docker-entrypoint.sh

WORKDIR /code

ARG PROD_JVM_OPTS
ARG PORT
ARG DOCKERIZE_VERSION

ENV JVM_OPTS=${PROD_JVM_OPTS}

RUN gradle clean build --no-daemon -x test

RUN wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
        && tar -C /usr/local/bin -xzvf "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
        && rm "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz"

EXPOSE ${PORT}

ENTRYPOINT ["/docker-entrypoint.sh"]
