FROM amazoncorretto:21-alpine-jdk

# Instalar fontconfig y libfreetype6 con Alpine Package Keeper (apk)
RUN apk add --no-cache fontconfig freetype

WORKDIR /app

# Copiar el archivo JAR generado por Spring Boot
COPY target/CasuarinasRest-0.0.1-SNAPSHOT.jar app.jar

ENV JAVA_TOOL_OPTIONS="-Djava.awt.headless=true"

# Ejecutar la aplicación en modo headless
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]
