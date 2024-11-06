FROM alpine/java:21-jdk

WORKDIR /app

COPY . .

RUN ./gradlew dependencies
RUN ./gradlew clean

CMD ["./gradlew", "bootRun"]