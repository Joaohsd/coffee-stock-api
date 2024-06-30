#!/bin/bash

echo "Sending email to $EMAIL_DEST..."
echo "Build has been executed correctly" | mail -s "Build for Coffee Stock API project" $EMAIL_DEST