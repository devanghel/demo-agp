FROM openjdk:8-jdk-alpine
RUN mvn clean install -DskipTests
RUN mvn clean package -DskipTests
RUN ./bash/export_env_dev.sh
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar", "-Dspring.profiles.active=dev"]