#!/bin/bash

mvn clean package
java -jar target/address-service-springboot.jar
