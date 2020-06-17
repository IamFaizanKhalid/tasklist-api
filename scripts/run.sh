#!/usr/bin/env bash

source ./scripts/env.sh

echo "Running server..."
sbt -Dhttp.port="$TASKLIST_PORT" run
