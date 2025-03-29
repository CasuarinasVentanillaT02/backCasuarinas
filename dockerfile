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
FROM maven:3.8.6-openjdk-11-slim AS builder
WORKDIR /workspace
COPY . .
RUN ./mvnw package

# Etapa de ejecución
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=builder /workspace/target/CasuarinasRest-0.0.1-SNAPSHOT.jar /app/app.jar

# Instalar dependencias necesarias para JasperReports
RUN apt-get update && apt-get install -y \
    libfreetype6 \
    fontconfig \
    && rm -rf /var/lib/apt/lists/*

# Configurar la aplicación para modo headless
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "/app/app.jar"]