FROM openjdk:7
FROM maven:3.6.0-jdk-11-slim AS build
# install git
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y git && \
    apt-get install gnupg2 -y
COPY . /cronitor
WORKDIR /cronitor
RUN mvn -f pom.xml clean package


# To release a new version the following commands need to be run from the container
# gpg --gen-key
# gpg --send-keys --keyserver keys.openpgp.org
# mvn mvn versions:set -DnewVersion=X.X.X
# mvn clean deploy -s settings.xml -Prelease