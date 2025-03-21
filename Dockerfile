FROM openjdk:21-slim

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=build/libs/ordermanagement-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} ordermanagement.jar


#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/ordermanagement.jar"]