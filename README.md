# wedding-scoop-backend

# 배포
저는 오라클클라우드가 무료여서 인스턴스 오라클클라우드에 배포했는데 인스턴스 내려가서 편하실걸로 하시면 될 것 같아요
docker 파일 각 모듈 안에 docker 밑에 있습니다 필요하면 쓰시면 될 것 같슴다

# 모듈
- auth : 아무것도 없습니다
- scoop
  * filter : 로그인 필터
  * domain : member, vendor, weddingnote 별로 분리
  * support
    * DataUploader : csv 데이터파일 업로드 워커
    * ImageUrlGenerator : image 업로드
      > 이부분 저는 오라클 클라우드 오브젝트 스토리지로 구현했는데 minIO, S3 등 편하신걸로 다시 만드시면 될 것 갔습니다.
    * JwtTokenProvider : 간단한 토큰 생성

# 테스트
TDD로는 안해서.... 테스트 코드가 없슴니다... 죄송합니다

# erd
<img width="2440" height="1822" alt="wedding scoop" src="https://github.com/user-attachments/assets/144884ce-3ae5-48c4-b20d-be41075ffc13" />


# excel data

https://www.notion.so/Data-16ceba289e6980a181eded44e5b7d998?pvs=4
