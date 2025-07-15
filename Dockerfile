
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .


RUN chmod +x mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Expose the port
EXPOSE 8080

# Start the Spring Boot app
CMD ["java", "-jar", "target/job-portal-backend-0.0.1-SNAPSHOT.jar"]
