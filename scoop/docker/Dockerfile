FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./target/scoop.jar scoop.jar
ENTRYPOINT ["java", "-jar", "/app/scoop.jar", "--spring.profiles.active=prod"]