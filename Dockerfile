
FROM eclipse-temurin:17-jdk-alpine


WORKDIR /app


COPY target/libreri-0.0.1-SNAPSHOT.jar /app/library-system.jar


EXPOSE 8080


ENV SPRING_PROFILES_ACTIVE=prod


ENTRYPOINT ["java", "-jar", "/app/library-system.jar"]
