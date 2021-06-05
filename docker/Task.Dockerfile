FROM ubuntu:latest
RUN apt-get update && apt-get upgrade -y
RUN apt-get install maven -y
RUN apt-get install openjdk-11-jdk -y
RUN mkdir dev


COPY ../src dev/app/src
COPY ../pom.xml dev/app
RUN mvn -f dev/pom.xml clean package


COPY --from=build ../target/demo1-0.0.1-SNAPSHOT.jar dev/app/demo.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","dev/app/demo.jar"]