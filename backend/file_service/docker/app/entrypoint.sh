#!/usr/bin/env sh

set -o errexit
set -o nounset

cmd="$*"

if [ "$1" = 'launch-app' ]; then
  yarn run launch-app --env dev
elif [ "$1" = 'launch-tests']; then
  yarn run launch-tests --env test
else
  exec "$cmd"
fi