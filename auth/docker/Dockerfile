FROM eclipse-temurin:21-jre
WORKDIR /app
# 디버깅: 현재 디렉토리 및 파일 목록 확인
RUN echo "Current Directory: $(pwd)" && ls -al /app
COPY ./auth/target/*.jar auth.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev}","-Dserver.port=8080","-jar","/app/auth.jar"]