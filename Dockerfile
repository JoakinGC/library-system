# Utilizar una imagen base de Java 17 (o la versión compatible con tu proyecto)
FROM eclipse-temurin:17-jdk-alpine

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR del proyecto en el contenedor
COPY target/libreri-0.0.1-SNAPSHOT.jar /app/library-system.jar

# Exponer el puerto en el que se ejecutará la aplicación
EXPOSE 8080

# Establecer la variable de entorno para Spring Boot (opcional)
ENV SPRING_PROFILES_ACTIVE=prod

# Comando para ejecutar la aplicación Spring Boot
ENTRYPOINT ["java", "-jar", "/app/library-system.jar"]
