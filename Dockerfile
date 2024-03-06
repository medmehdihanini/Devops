# Use a base image with Maven for building the application
FROM openjdk:17-alpine AS build

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

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port and run the application
EXPOSE 9090
CMD ["java", "-jar", "app.jar"]
