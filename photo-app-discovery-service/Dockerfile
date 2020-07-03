FROM openjdk:8-jdk-alpine
COPY target/photo-app-discovery-service-0.0.1-SNAPSHOT.jar DiscoveryService.jar
ENTRYPOINT ["java", "-jar", "DiscoveryService.jar"]

# docker build --tag=name --force-rm=true . (force-rm is using for clean up after image nas been created)