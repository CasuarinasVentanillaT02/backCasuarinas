FROM amazoncorretto:21-alpine-jdk

RUN apt-get update && apt-get install -y fontconfig libfreetype6 && rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY target/CasuarinasRest-0.0.1-SNAPSHOT.jar app.jar

#ENTRYPOINT ["java", "-jar" , "/app.jar"]
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "app.jar"]