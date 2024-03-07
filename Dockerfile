# Use a base image with Alpine Linux and Maven for building
FROM maven:3.8.8-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files
COPY pom.xml ./
COPY src ./src

# Download dependencies and cache them in Docker layer
RUN mvn dependency:go-offline -B

# Package the application (skip tests)
RUN mvn package -DskipTests

# Create a lightweight final image with the JAR file
FROM openjdk:17-alpine
    
# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 9090

# Command to run the application
CMD ["java", "-jar", "app.jar"]
