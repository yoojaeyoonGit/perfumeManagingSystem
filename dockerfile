FROM openjdk:11-jdk-slim as Build

COPY . /app

# 작업 디렉터리 설정
WORKDIR /app

# gradlew에 실행 권한 부여
RUN chmod +x gradlew


RUN ./gradlew build -x test

FROM openjdk:11-jdk-slim

ARG JAR_FILE=build/libs/perfumeManagingSystem-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "-s", "-t", "100", "--"]