#!/usr/bin/env sh

set -o errexit
set -o nounset

cmd="$*"

if [ "$1" = 'launch-prod' ]; then
  nginx -g 'daemon off;'
else
  exec "$cmd"
fi