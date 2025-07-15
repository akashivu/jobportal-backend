# Start from official OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Package the application
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/job-portal-backend-0.0.1-SNAPSHOT.jar"]
