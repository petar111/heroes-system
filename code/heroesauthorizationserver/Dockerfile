FROM openjdk:18-jdk-alpine3.14

WORKDIR /app

COPY . .

RUN ./gradlew dependencies
RUN ./gradlew clean

CMD ["./gradlew", "bootRun"]
