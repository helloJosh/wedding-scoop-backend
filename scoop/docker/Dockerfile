FROM eclipse-temurin:21-jre
WORKDIR /app
COPY /home/runner/work/wedding-scoop-backend/wedding-scoop-backend/scoop/target/*.jar scoop.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=8081","-jar","/app/scoop.jar"]