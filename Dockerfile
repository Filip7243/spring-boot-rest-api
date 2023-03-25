FROM openjdk:17

COPY target/spring-boot-rest-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]