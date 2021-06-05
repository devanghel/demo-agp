FROM maven:3.5-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM gcr.io/distroless/java
COPY --from=build ../target/demo1-0.0.1-SNAPSHOT.jar /usr/app/demo.jar
EXPOSE 8080
# todo add spring profile flag
ENTRYPOINT ["java","-jar","/usr/app/demo.jar"]


# Source https://stackoverflow.com/a/27768965