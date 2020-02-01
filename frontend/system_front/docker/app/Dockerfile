FROM node:13-buster-slim

ARG PROD_PORT
ARG DEV_PORT

WORKDIR /code

COPY . .

COPY ./docker/app/entrypoint.sh /docker-entrypoint.sh
EXPOSE ${DEV_PORT}
EXPOSE ${PROD_PORT}

RUN yarn

ENTRYPOINT ["/docker-entrypoint.sh"]
