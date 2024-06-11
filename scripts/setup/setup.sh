#!/bin/bash

# Go to tests folder
cd tests/

# Installing dependencies
npm install

# Setup cypress 
./node_modules/.bin/cypress install