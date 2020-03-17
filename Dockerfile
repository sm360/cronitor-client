FROM openjdk:7
FROM maven:3.6.0-jdk-11-slim AS build
# install git
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y git
COPY . /cronitor
WORKDIR /cronitor
RUN mvn -f pom.xml clean package
