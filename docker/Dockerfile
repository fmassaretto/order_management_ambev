FROM gradle:8.13-jdk21-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src

USER root

RUN chown -R gradle /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build || return 0

COPY . .

RUN gradle clean build

FROM openjdk:21-slim

#EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/ordermanagement-0.0.1-SNAPSHOT.jar /app/ordermanagement.jar
#RUN ls /home/gradle/src/build/libs/ -all
#COPY build/libs/*.jar /app/ordermanagement.jar


#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#WORKDIR /home/gradle/src
#RUN ./gradlew spring-boot:run
ENTRYPOINT ["java","-jar","/app/ordermanagement.jar"]

#FROM openjdk:21-slim
#
#VOLUME /tmp
#
##EXPOSE 8080
#
#ARG JAR_FILE=build/libs/ordermanagement-0.0.1-SNAPSHOT.jar
#
#ADD ${JAR_FILE} ordermanagement.jar
#
#
##ARG JAR_FILE=target/*.jar
##COPY ${JAR_FILE} app.jar
#
#ENTRYPOINT ["java","-jar","/ordermanagement.jar"]