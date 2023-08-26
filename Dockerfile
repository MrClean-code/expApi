FROM openjdk:14-alpine
ADD /target/expApi-0.0.1-SNAPSHOT.jar backend-expApi.jar
ENTRYPOINT [ "java", "-jar", "backend-expApi.jar"]