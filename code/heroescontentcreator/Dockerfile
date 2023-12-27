FROM openjdk:18-jdk-alpine3.14
 
WORKDIR /app
 
COPY . .

RUN ./mvnw dependency:go-offline
 
CMD ["./mvnw", "spring-boot:run"]
