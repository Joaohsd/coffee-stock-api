#!/bin/bash

# Go to tests folder
cd tests/

# ./node_modules/.bin/cypress install

# Run all integration tests
./node_modules/.bin/cypress run --spec 'cypress/api/**/' --browser electron