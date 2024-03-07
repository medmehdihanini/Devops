# Use a base image with Alpine Linux and OpenJDK 17 for building
FROM openjdk:17-alpine AS build

# Set the working directory in the container
WORKDIR /app

# Copy Maven and JDK from Jenkins image
COPY --from=jenkins /usr/share/maven /usr/share/maven
COPY --from=jenkins /usr/lib/jvm/java-17-openjdk-amd64 /usr/lib/jvm/java-17-openjdk-amd64

# Set environment variables for Maven and Java
ENV MAVEN_HOME=/usr/share/maven
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$MAVEN_HOME/bin:$JAVA_HOME/bin:$PATH

# Copy the project files
COPY pom.xml ./

# Download dependencies and cache them in Docker layer
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

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
