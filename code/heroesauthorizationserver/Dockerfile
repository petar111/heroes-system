FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

COPY . .

RUN ./gradlew dependencies

CMD ["./gradlew", "bootRun"]
