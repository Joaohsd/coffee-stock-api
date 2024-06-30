#!/bin/bash

echo 'Log in...'
echo "$DOCKERHUB_CREDS_PSW" | docker login -u "$DOCKERHUB_CREDS_USR" --password-stdin

echo 'Tagging image...'
docker tag coffee-image:latest "$DOCKERHUB_REPO:latest"

echo 'Pushing image...'
docker push "$DOCKERHUB_REPO:latest"