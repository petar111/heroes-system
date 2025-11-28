# Use a base Alpine image
FROM maven:3-eclipse-temurin-21-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app/app.jar"]