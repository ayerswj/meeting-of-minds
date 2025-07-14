#!/bin/bash
docker volume create mydata
docker run --rm -v mydata:/data busybox sh -c 'echo hello > /data/hello.txt'
docker run --rm -v mydata:/data busybox cat /data/hello.txt