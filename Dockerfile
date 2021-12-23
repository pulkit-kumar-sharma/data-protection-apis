FROM openjdk:11-jre-slim
MAINTAINER Pulkit Sharma
COPY target/dataprotection-0.0.1-SNAPSHOT.jar dataprotection.jar
ENTRYPOINT ["java","-jar","/dataprotection.jar"]