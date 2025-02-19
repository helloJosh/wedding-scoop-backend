FROM eclipse-temurin:21-jre
WORKDIR /app
COPY /home/runner/work/wedding-scoop-backend/wedding-scoop-backend/auth/target/*.jar auth.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=8080","-jar","/app/auth.jar"]