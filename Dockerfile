FROM openjdk:17-alpine
ADD /target/The-Red-Mist-0.0.1-SNAPSHOT.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]