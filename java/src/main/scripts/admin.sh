#!/bin/sh
sudo kill -9 `sudo lsof -t -i:8080`
java -jar /var/lib/jenkins/workspace/demo-project/target/demo-project.jar