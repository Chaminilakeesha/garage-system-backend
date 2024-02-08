FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY build/libs/garage-system-backend-0.0.1-SNAPSHOT.jar /app/garage-system-backend-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "garage-system-backend-0.0.1-SNAPSHOT.jar"]
