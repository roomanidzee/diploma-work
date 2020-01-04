#!/usr/bin/env sh

set -o errexit
set -o nounset

mongo_ready(){
  dockerize -wait tcp://std_mongo:27017 -timeout 10s
}

ch_ready(){
  dockerize -wait tcp://std_clickhouse:8401 -timeout 10s
}

until mongo_ready && ch_ready; do
  >&2 printf '\nDatabases unavailable - sleeping'
  >&2 printf '\n'
  >&2 printf '\n'
done

cmd="$*"

if [ "$1" = 'launch-prod' ]; then
  gradle clean build -x test && java "$JVM_OPTS" -jar /dist/app.jar
elif [ "$1" = 'launch-dev' ]; then
  gradle clean bootRun
else
  exec "$cmd"
fi
