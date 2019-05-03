FROM openjdk:8-jdk-alpine
ADD target/data_server.jar data_server.jar
ENTRYPOINT ["java", "-jar", "data_server.jar"]