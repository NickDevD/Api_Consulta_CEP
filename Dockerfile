FROM openjdk:22-bookworm

LABEL maintainer="Nicholas Monteiro <nick.developerdev@gmail.com>"

WORKDIR /app

COPY /target/Api_Consulta_CEP-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]