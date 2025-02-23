FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./target/*.jar scoop.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/app/scoop.jar"]