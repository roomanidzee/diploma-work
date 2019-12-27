FROM openjdk:11.0.5-stretch
LABEL maintainer = 'Andrey Romanov'

ARG JAR_FILE
ARG JVM_OPTS
ARG PORT

ENV JVM_OPTS=${JVM_OPTS}

WORKDIR /usr/src/application

COPY ./docker/app/entrypoint.sh /docker-entrypoint.sh
COPY ${JAR_FILE} app.jar
EXPOSE ${PORT}

ENTRYPOINT ["/docker-entrypoint.sh"]
