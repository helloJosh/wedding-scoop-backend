FROM eclipse-temurin:21-jre
WORKDIR /app
COPY ./target/*.jar scoop.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=30305","-jar","/app/scoop.jar"]