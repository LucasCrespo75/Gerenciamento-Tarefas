FROM maven:3.8.1-openjdk-17-slim AS build

WORKDIR /app

COPY user-service/pom.xml /app/
COPY user-service/src/ /app/src/

RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/user-service.jar"]