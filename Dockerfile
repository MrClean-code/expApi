FROM openjdk:14-alpine
ADD /target/expApi-0.0.1-SNAPSHOT.jar backend-practic.jar
ENTRYPOINT [ "java", "-jar", "backend-practic.jar"]