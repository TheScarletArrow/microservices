# Use a base image with Java 17 installed
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file to the container
COPY target/discovery-0.0.1-SNAPSHOT.jar /app/your-spring-boot-app.jar

# Expose the port your Spring Boot application is listening on (change the port if necessary)
EXPOSE 8761

# Command to run the Spring Boot application
CMD ["java", "-jar", "your-spring-boot-app.jar"]