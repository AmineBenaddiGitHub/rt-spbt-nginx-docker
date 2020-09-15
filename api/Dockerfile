# Build stage
FROM maven:3.6.0-jdk-11-slim AS build
# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build
RUN mvn dependency:go-offline
#Copy source code
COPY src /build/src
# Build application
RUN mvn package -DskipTests

# Package stage
FROM openjdk:8
COPY --from=build /build/target/spamdetector-0.0.1-SNAPSHOT.jar spamdetector-0.0.1-SNAPSHOT.jar
EXPOSE 9999
ENTRYPOINT ["java","-jar","spamdetector-0.0.1-SNAPSHOT.jar"]
