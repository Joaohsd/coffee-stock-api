# Stage 1: Build Stage
FROM openjdk:17-jdk-alpine AS build
WORKDIR /usr/src/app

# Copy only the necessary files for dependency resolution
COPY build.gradle.kts ./
COPY settings.gradle.kts ./
COPY src/main/kotlin ./src/main/kotlin
COPY src/main/resources ./src/main/resources
COPY gradle ./gradle
COPY gradlew ./

# Build the application
RUN ./gradlew clean build

# Stage 2: Runtime Stage
FROM openjdk:17-jdk-alpine
WORKDIR /usr/src/app

# Copy the built JAR file from the build stage
COPY --from=build /usr/src/app/build/libs/coffeestock-0.0.1-SNAPSHOT.jar .

# Expose the port on which your application will run (if applicable)
EXPOSE 9000

# Define the command to run your application
CMD ["java", "-jar", "coffeestock-0.0.1-SNAPSHOT.jar"]
