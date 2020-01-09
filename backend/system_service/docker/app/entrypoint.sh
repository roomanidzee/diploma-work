#!/usr/bin/env sh

set -o errexit
set -o nounset

mongo_ready(){
  dockerize -wait tcp://std_mongo:27017 -timeout 10s
}

until mongo_ready; do
  >&2 printf '\nDatabase unavailable - sleeping'
  >&2 printf '\n'
  >&2 printf '\n'
done

cmd="$*"

elif [ "$1" = 'launch-dev' ]; then
  gradle bootRun
elif [ "$1" = 'launch-prod' ]; then
  java "$JVM_OPTS" -jar build/libs/app.jar
elif [ "$1" = 'launch-tests' ]; then
  gradle test
else
  exec "$cmd"
fi
