#!/bin/bash

direnv allow

mvn clean package

java -jar target/article-service-springboot.jar
