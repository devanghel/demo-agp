#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/demo1-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]



# Source https://stackoverflow.com/a/27768965