#!/bin/bash

# Remove network
echo 'Removing network...'
docker network disconnect myNetwork jenkins
docker network disconnect myNetwork api
docker network disconnect myNetwork db
docker network rm myNetwork

# Stop containers
echo 'Stopping containers...'
docker stop api
docker stop db

# Remove containers
docker rm api
docker rm db

# Remove images
echo 'Removing images...'
docker rmi -f mysql:8.4.0
docker rmi -f coffee-image:latest
docker rmi -f joaohsd/coffee-stock:latest

# Remove volumes
echo 'Removing volumes...'
docker volume rm -f my-db