FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/PhotoAppApiAlbums-0.0.1-SNAPSHOT.jar PhotoApiAlbums.jar
ENTRYPOINT ["java", "-jar", "PhotoApiAlbums.jar"]