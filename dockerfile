# #FROM amazoncorretto:21-alpine-jdk
# FROM eclipse-temurin:21-jre-jammy


# # Instalar fontconfig y libfreetype6 con Alpine Package Keeper (apk)
# RUN apk add --no-cache fontconfig freetype

# WORKDIR /app

# # Copiar el archivo JAR generado por Spring Boot
# COPY target/CasuarinasRest-0.0.1-SNAPSHOT.jar app.jar

# ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true"

# # Ejecutar la aplicación en modo headless
# ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]
# Etapa de construcción
# Usa una imagen base de OpenJDK 21
FROM openjdk:21-jdk-slim

# Crea un directorio de trabajo
WORKDIR /app

# Copia el archivo JAR compilado al contenedor
COPY target/CasuarinasRest-0.0.1-SNAPSHOT.jar /app/app.jar

# Instala las dependencias necesarias para JasperReports
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

# Configura la aplicación para modo headless
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "/app/app.jar"]