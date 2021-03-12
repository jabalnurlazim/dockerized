#!/bin/bash

# rm -rf target  # uncomment to automate the whole maven lifecycle via restart
if [ ! -d "target" ]; then
  mvn clean install && mvn compile && mvn clean package
fi
java -jar target/demo-project.jar