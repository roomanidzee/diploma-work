#!/usr/bin/env sh

set -o errexit
set -o nounset

cmd="$*"

if [ "$1" = 'launch-tests' ]; then
  yarn run launch-tests
elif [ "$1" = 'launch-dev' ]; then
  yarn run launch-dev
else
  exec "$cmd"
fi