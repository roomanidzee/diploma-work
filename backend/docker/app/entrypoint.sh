#!/usr/bin/env sh

set -o errexit
set -o nounset

cmd="$*"

if [ "$1" = 'launch' ]; then
  java "$JVM_OPTS" -jar app.jar
else
  exec "$cmd"
fi
