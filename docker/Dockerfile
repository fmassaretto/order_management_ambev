FROM gradle:8.13-jdk21-alpine AS TEMP_BUILD_IMAGE

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build.gradle settings.gradle $APP_HOME

COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . /home/gradle/src
#USER root
RUN chown -R gradle /home/gradle/src

RUN gradle build || return 0
COPY . .
RUN gradle clean build

# actual container
FROM openjdk:21-slim
ENV ARTIFACT_NAME=ordermanagement-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/

WORKDIR $APP_HOME
COPY --from=TEMP_BUILD_IMAGE $APP_HOME/build/libs/$ARTIFACT_NAME .

EXPOSE 8080
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}

#COPY --chown=gradle:gradle . /home/gradle/src
#
#USER root
#
#RUN chown -R gradle /home/gradle/src
#
#WORKDIR /home/gradle/src
#
#RUN gradle build || return 0
#
#COPY . .
#
#RUN gradle clean build
#
#FROM openjdk:21-slim
#
##EXPOSE 8080
#
#RUN mkdir /app
#
#COPY --from=build /home/gradle/src/build/libs/ordermanagement-0.0.1-SNAPSHOT.jar /app/ordermanagement.jar
##RUN ls /home/gradle/src/build/libs/ -all
##COPY build/libs/*.jar /app/ordermanagement.jar
#
#
##ARG JAR_FILE=target/*.jar
##COPY ${JAR_FILE} app.jar
##WORKDIR /home/gradle/src
##RUN ./gradlew spring-boot:run
#ENTRYPOINT ["java","-jar","/app/ordermanagement.jar"]
#
##FROM openjdk:21-slim
##
##VOLUME /tmp
##
###EXPOSE 8080
##
##ARG JAR_FILE=build/libs/ordermanagement-0.0.1-SNAPSHOT.jar
##
##ADD ${JAR_FILE} ordermanagement.jar
##
##
###ARG JAR_FILE=target/*.jar
###COPY ${JAR_FILE} app.jar
##
##ENTRYPOINT ["java","-jar","/ordermanagement.jar"]