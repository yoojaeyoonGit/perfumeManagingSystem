FROM openjdk:11-jre-slim

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "-s", "-t", "100", "--"]