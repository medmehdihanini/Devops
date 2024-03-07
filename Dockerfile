# Use a base image with Maven and Java for building the application
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project files and download dependencies
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Copy the source code and package the application
COPY src ./src
RUN mvn package -DskipTests

# Create a lightweight final image with the JAR file
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the final image
COPY --from=build /app/target/*.jar app.jar

# Expose the port and run the application
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]
