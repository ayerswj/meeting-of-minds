#!/bin/bash
docker swarm init
docker service create --name web --publish 8080:80 nginx
docker service scale web=3