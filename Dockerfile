# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS build

# Set the working directory to the root of the project
WORKDIR /app

# Copy pom.xml and source code
COPY pom.xml . 
COPY src ./src

# Package the application, skipping tests
RUN mvn clean package -DskipTests

# Stage 2: Create the final image
FROM openjdk:17-jdk-slim

# Set the working directory to the root of the project
WORKDIR /app

# Copy the Maven build output (JAR file) into the container
COPY --from=build /app/target/vtpp5a_ssf_day_13-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application will run on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
