#!/bin/bash
docker network create mynet
docker run -d --name c1 --network mynet busybox sleep 3600
docker run -d --name c2 --network mynet busybox sleep 3600
docker exec c1 ping -c 3 c2