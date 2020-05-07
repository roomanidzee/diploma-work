#!/usr/bin/env sh

set -o errexit
set -o nounset

cmd="$*"

if [ "$1" = 'launch-app' ]; then
    python run.py
elif [ "$1" = 'launch-worker' ]; then
    arq app.settings.arq.WorkerSettings
else
    exec "$cmd"
fi