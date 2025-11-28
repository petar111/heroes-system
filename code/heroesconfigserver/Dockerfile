# Use a base Alpine image
FROM gradle:jdk21-alpine

USER 0

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

RUN mkdir -p /config/local-repo

COPY /config-git /config/local-repo

ENTRYPOINT ["java","-jar","/app/app.jar"]
