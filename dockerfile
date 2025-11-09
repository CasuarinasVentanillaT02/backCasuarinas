# FROM openjdk:21-jdk-slim
FROM eclipse-temurin:21-jdk-jammy


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