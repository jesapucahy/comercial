#!/usr/bin/env bash
# wait-for-it.sh

set -e

host="$1"
shift
cmd="$@"

until nc -z "$host" 9091; do
  echo "Kafka is unavailable - waiting"
  sleep 1
done

echo "Kafka is up - executing command"
exec $cmd