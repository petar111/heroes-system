FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

COPY .gradle .gradle/
COPY gradlew build.gradle ./

RUN ./gradlew dependency:go-offline

COPY src ./src

CMD ["./gradlew", "bootRun"]