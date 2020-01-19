FROM node:13-buster-slim

ARG PORT

WORKDIR /code

COPY . .

COPY ./docker/app/entrypoint.sh /docker-entrypoint.sh
EXPOSE ${PORT}

RUN yarn

ENTRYPOINT ["/docker-entrypoint.sh"]
