#!/usr/bin/env sh

set -o errexit
set -o nounset

mongo_ready(){
  dockerize -wait tcp://std_mongo:27017 -timeout 10s
}

until mongo_ready; do
  >&2 printf '\nDatabases unavailable - sleeping'
  >&2 printf '\n'
  >&2 printf '\n'
done

cmd="$*"

if [ "$1" = 'launch' ]; then
  java "$JVM_OPTS" -jar app.jar
else
  exec "$cmd"
fi
