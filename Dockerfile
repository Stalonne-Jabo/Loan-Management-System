# Use official Java runtime
FROM openjdk:17

# Set working directory
WORKDIR /app

# Copy JAR file into container
COPY LoanSystem.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]