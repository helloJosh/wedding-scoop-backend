FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=8081","-jar","/app/app.jar"]