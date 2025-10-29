FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/perrosenlacalle-0.0.1.jar
COPY ${JAR_FILE} app_perrosenlacalle.jar
EXPOSE 9088
ENTRYPOINT ["java", "-jar", "app_perrosenlacalle.jar"]