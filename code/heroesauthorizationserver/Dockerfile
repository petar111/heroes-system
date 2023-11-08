FROM eclipse-temurin:18-jdk-focal

WORKDIR /app

COPY .gradle .gradle/
COPY gradlew build.gradle ./

RUN ./gradlew dependency:go-offline

COPY src ./src

CMD ["./gradlew", "bootRun"]
