FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test

FROM openjdk:17-jdk-slim
mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

EXPOSE 8080
ENTRYPOINT ["top", "-b"]