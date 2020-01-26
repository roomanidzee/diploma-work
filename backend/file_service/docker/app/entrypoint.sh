#!/usr/bin/env sh

set -o errexit
set -o nounset

cmd="$*"

if [ "$1" = 'launch-dev' ]; then
  yarn run launch-dev --env dev
else
  exec "$cmd"
fi