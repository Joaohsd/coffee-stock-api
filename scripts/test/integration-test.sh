#!/bin/bash

# Go to tests folder
cd tests/

# Run all integration tests
./node_modules/.bin/cypress run --spec 'cypress/api/**/' --browser electron