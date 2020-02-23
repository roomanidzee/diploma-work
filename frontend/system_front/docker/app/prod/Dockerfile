FROM node:13-buster-slim as build

WORKDIR /code

COPY . .

RUN yarn
RUN yarn run launch-build

FROM nginx:1.17-alpine

COPY --from=build /code/dist /usr/share/nginx/html

RUN rm /etc/nginx/conf.d/default.conf

COPY ./docker/nginx/nginx.conf /etc/nginx/conf.d

COPY ./docker/app/prod/entrypoint.sh /docker-entrypoint.sh
EXPOSE 3000

ENTRYPOINT ["/docker-entrypoint.sh"]