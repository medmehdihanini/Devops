# Use a base image with Maven for building the application
FROM openjdk-17-jdk AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml .

# Download dependencies and cache them in Docker layer
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Create a lightweight Docker image with the JAR file
FROM openjdk-17

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 9090

# Command to run the application
CMD ["java", "-jar", "app.jar"]
