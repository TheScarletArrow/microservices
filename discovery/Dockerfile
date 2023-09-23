# Step 1: Resolve Maven dependencies and build the application
FROM maven:3.8.3-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy only the pom.xml to leverage Docker caching
COPY pom.xml .

# Resolve Maven dependencies, caching them
RUN mvn dependency:go-offline

# Copy the entire project and build the application
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Create the runtime image
# Use a smaller base image for the runtime environment
FROM bellsoft/liberica-openjdk-alpine:17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /app/target/*.jar app.jar

# Set the entry point for the application
ENTRYPOINT ["java", "-jar", "app.jar"]
