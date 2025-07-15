# Use Java 21 base image
FROM eclipse-temurin:21-jdk-alpine

# Set working directory
WORKDIR /app

# Copy files
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the port
EXPOSE 8080

# Run the JAR
CMD ["java", "-jar", "target/job-portal-backend-0.0.1-SNAPSHOT.jar"]
