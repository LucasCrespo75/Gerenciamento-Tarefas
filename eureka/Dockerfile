FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml /app/
COPY src/ /app/src/
RUN mvn clean install -Dmaven.test.skip=true

FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y curl --no-install-recommends && rm -rf /var/lib/apt/lists/*
COPY --from=build /app/target/*.jar /app/eureka-server.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "/app/eureka-server.jar"]