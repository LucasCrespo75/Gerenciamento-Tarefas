FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

COPY task-service/pom.xml /app/
COPY task-service/src/ /app/src/


RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/task-service-0.0.1-SNAPSHOT.jar /app/task-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/task-service.jar"]
