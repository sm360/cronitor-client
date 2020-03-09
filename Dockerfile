FROM openjdk:7
FROM maven:3.6.0-jdk-11-slim AS build
COPY . /cronitor
WORKDIR /cronitor
RUN mvn -f pom.xml clean package
