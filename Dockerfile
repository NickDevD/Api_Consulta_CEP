# BUILD FASE

FROM maven:3.9.6-eclipse-temurin-21 AS builder
LABEL maintainer="Nicholas Monteiro <nick.developerdev@gmail.com>"
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# RUN FASE
FROM amazoncorretto:22
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
