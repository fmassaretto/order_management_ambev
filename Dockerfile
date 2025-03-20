FROM gradle:8.13-jdk21-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:21-slim

#EXPOSE 8080

RUN mkdir /app

RUN ls /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/ordermanagement.jar
#COPY build/libs/*.jar /app/ordermanagement.jar


#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","ordermanagement.jar"]