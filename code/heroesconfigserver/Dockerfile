FROM openjdk:18-jdk-alpine3.14 as final-app

USER 0

WORKDIR /app

COPY . .

RUN ./gradlew dependencies
RUN ./gradlew clean

RUN mkdir -p /config/local-repo

COPY /config-git /config/local-repo

CMD ["./gradlew", "bootRun"]
