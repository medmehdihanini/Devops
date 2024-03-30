# Use a base image with JDK installed
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified working directory
COPY target/tp1yassinejallouli4twin7-1.0.1-SNAPSHOT.jar /app/tp1yassinejallouli4twin7-1.0.1-SNAPSHOT.jar

# Specify the command to run your application when the container starts
CMD ["java", "-jar", "tp1yassinejallouli4twin7-1.0.1-SNAPSHOT.jar"]