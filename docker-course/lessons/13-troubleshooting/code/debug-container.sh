#!/bin/bash
# Start a container that will fail
docker run --name failtest busybox sh -c 'exit 1'
# View logs
docker logs failtest
# Try to exec into a running container (will fail if not running)
docker exec -it failtest sh || echo 'Container not running'