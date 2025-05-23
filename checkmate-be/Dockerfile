# 1단계: 빌드 단계
FROM gradle:8.8-jdk17 AS build

# 작업 디렉토리 설정
WORKDIR /home/app

# Gradle 파일 복사
COPY build.gradle /home/app/
COPY settings.gradle /home/app/

# 종속성 미리 다운로드
RUN gradle build -x test --parallel --continue || true

# 소스 코드 복사
COPY src /home/app/src

# 프로젝트 빌드
RUN gradle clean build -x test

# 2단계: 실행 단계
FROM eclipse-temurin:17-jre

# 1) 기본 툴 및 lsb-release 설치 (캐시 삭제는 뒤로)
RUN apt-get update && apt-get install -y --no-install-recommends \
      curl gnupg ca-certificates lsb-release \
 && rm -rf /var/lib/apt/lists/*

# 2) HamoniKR PPA 등록
RUN curl -sL https://repo.hamonikr.org/hamonikr-app.apt | bash -E

# 3) 패키지 리스트 갱신 후 LibreOffice + HWP 필터 + 폰트 설치
RUN apt-get update && apt-get install -y --no-install-recommends \
      libreoffice \
      libreoffice-core \
      libreoffice-writer \
      libreoffice-calc \
      libreoffice-hwp2odt \
      fonts-nanum \
      fontconfig \
 && rm -rf /var/lib/apt/lists/*

# 빌드 결과물 복사
COPY --from=build /home/app/build/libs/*.jar /app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
