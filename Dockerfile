FROM openjdk:17-jdk
WORKDIR /app
COPY target/norsify-api-0.0.1-SNAPSHOT.jar norsify-api-0.0.1-SNAPSHOT.jar
VOLUME /tmp
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "norsify-api-0.0.1-SNAPSHOT.jar"]