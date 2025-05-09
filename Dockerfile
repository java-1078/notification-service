FROM openjdk:21-jdk-slim
WORKDIR /app
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar /app/notification-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/notification-service.jar"]