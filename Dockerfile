FROM maven:3.8.5-openjdk-17 AS build

RUN mkdir -p /app/source

COPY . /app/source

RUN mvn -f /app/source/pom.xml clean package -Dmaven.test.skip=true

FROM openjdk:17-ea-jdk-alpine3.14

COPY --from=build /app/source/target/*.jar /app/backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/backend.jar"]
