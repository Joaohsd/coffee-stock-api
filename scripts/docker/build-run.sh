#!/bin/bash

echo 'Building docker image...'
docker build -t coffee-image -f Dockerfile-API .

echo 'Running containers...'
docker compose -f docker-compose-test.yml up -d

sleep 20

echo 'Creating network...'
docker network create myNetwork

echo 'Connecting containers to network...'
docker network connect myNetwork db
docker network connect myNetwork api
docker network connect myNetwork jenkins

sleep 5