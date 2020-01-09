FROM node:13-buster-slim

ARG PORT

WORKDIR /usr/src/application

COPY . .

COPY ./docker/app/entrypoint.sh /docker-entrypoint.sh
EXPOSE ${PORT}

RUN yarn

ENTRYPOINT ["/docker-entrypoint.sh"]
