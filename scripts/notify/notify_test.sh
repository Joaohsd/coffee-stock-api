#!/bin/bash

echo "Sending email to $EMAIL_DEST..."
echo "All tests have been executed correctly" | mail -s "Test results for Coffee Stock API project" $EMAIL_DEST