#!/usr/bin/env bash

source ./scripts/env.sh

usage() {
  printf "Usage: $0 [-c] [ -p PORT ]\n\n"
  printf "\t-c to clean the project\n"
  printf "\t-p PORT to specify port (defaulted to $TASKLIST_PORT)\n\n"

  printf "Example:\n"
  printf "\t./scripts/run.sh -c -p 3456\n"
  exit 2
}

while getopts "lcup:?h" opt; do
  case $opt in
  c) SHOULD_CLEAN=true ;;
  p) TASKLIST_PORT=$OPTARG ;;
  h | ?) usage ;;
  esac
done

if [[ -n "$SHOULD_CLEAN" ]]; then
  echo "Cleaning..."
  sbt clean
fi

echo "Running server..."
sbt -Dhttp.port="$TASKLIST_PORT" run
