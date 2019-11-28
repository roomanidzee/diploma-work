FROM openjdk:11.0.5-stretch
LABEL maintainer = 'Andrey Romanov'

ARG JAR_FILE
ARG JVM_OPTS
ARG PORT

WORKDIR /usr/src/application

COPY ${JAR_FILE} app.jar
EXPOSE ${PORT}

ENTRYPOINT java ${JVM_OPTS} -jar app.jar
