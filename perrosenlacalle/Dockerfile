# Etapa de compilación
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

# Etapa de runtime
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app_perrosenlacalle.jar
EXPOSE 9088
ENTRYPOINT ["sh", "-c", "java -Dserver.port=${PORT:-9088} -jar app_perrosenlacalle.jar"]