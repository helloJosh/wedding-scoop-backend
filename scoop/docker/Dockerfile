FROM eclipse-temurin:21-jre
WORKDIR /app

# 디버깅: 현재 디렉토리 및 파일 목록 확인
RUN echo "Current Directory: $(pwd)" && ls -al /app
COPY ./scoop/target/*.jar scoop.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=8081","-jar","/app/scoop.jar"]