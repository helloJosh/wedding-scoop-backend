FROM eclipse-temurin:21-jre
WORKDIR /app
COPY auth/target/*.jar auth.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=8080","-jar","/app/auth.jar"]