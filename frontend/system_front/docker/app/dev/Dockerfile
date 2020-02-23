FROM node:13-buster-slim

ARG DEV_PORT

WORKDIR /code

COPY . .

COPY ./docker/app/dev/entrypoint.sh /docker-entrypoint.sh
EXPOSE ${DEV_PORT}

RUN yarn

ENTRYPOINT ["/docker-entrypoint.sh"]
