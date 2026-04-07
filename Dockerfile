FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY target/ems-app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
