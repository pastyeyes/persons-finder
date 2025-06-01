# Dockerfile for building and running the Spring Boot application

# Build stage
FROM gradle:8.14.1-jdk17-alpine AS builder
WORKDIR /home/temp/tech-test

# Copy source and build
COPY . .
RUN gradle clean bootJar -x test  --no-daemon

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy jar from builder
COPY --from=builder /home/temp/tech-test/build/libs/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
