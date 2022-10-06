FROM gradle:jdk18 as build
COPY . /app
WORKDIR /app

FROM openjdk:18-oracle
EXPOSE 8080
COPY --from=build /app/build/libs/Mystery-App-Javalin-1.0-SNAPSHOT.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java","-jar","app.jar"]