FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./target/*.jar scoop.jar
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app/scoop.jar"]