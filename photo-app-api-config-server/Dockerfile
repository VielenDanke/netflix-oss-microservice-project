FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/photo-app-api-config-server-0.0.1-SNAPSHOT.jar ConfigServer.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "ConfigServer.jar"]