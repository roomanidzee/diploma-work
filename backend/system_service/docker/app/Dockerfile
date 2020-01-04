FROM gradle:6.0.1-jdk11 as build
LABEL maintainer = 'Andrey Romanov'
COPY --chown=gradle:gradle . /home/gradle/app_src
WORKDIR /home/gradle/app_src
RUN gradle clean build --no-daemon -x test

FROM openjdk:11.0.5-stretch

ARG PROD_JVM_OPTS
ARG PORT
ARG DOCKERIZE_VERSION

ENV JVM_OPTS=${PROD_JVM_OPTS}

RUN wget "https://github.com/jwilder/dockerize/releases/download/${DOCKERIZE_VERSION}/dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
        && tar -C /usr/local/bin -xzvf "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz" \
        && rm "dockerize-alpine-linux-amd64-${DOCKERIZE_VERSION}.tar.gz"

COPY ./docker/app/entrypoint.sh /docker-entrypoint.sh
EXPOSE ${PORT}

RUN mkdir /dist
COPY --from=build /home/gradle/app_src/build/libs/*.jar /dist/app.jar

ENTRYPOINT ["/docker-entrypoint.sh"]
