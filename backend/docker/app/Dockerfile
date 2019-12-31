FROM openjdk:11.0.5-stretch
LABEL maintainer = 'Andrey Romanov'

ARG JAR_FILE
ARG JVM_OPTS
ARG PORT
ARG DOCKERIZE_VERSION

ENV JVM_OPTS=${JVM_OPTS}

WORKDIR /usr/src/application

RUN wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
        && tar -C /usr/local/bin -xzvf "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
        && rm "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz"

COPY ./docker/app/entrypoint.sh /docker-entrypoint.sh
COPY ${JAR_FILE} app.jar
EXPOSE ${PORT}

ENTRYPOINT ["/docker-entrypoint.sh"]
