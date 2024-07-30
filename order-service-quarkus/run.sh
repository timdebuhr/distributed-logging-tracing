#!/bin/bash

direnv allow

mvn clean package

java -jar target/quarkus-app/quarkus-run.jar
