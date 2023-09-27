FROM eclipse-temurin:17-jdk
VOLUME /tmp

COPY target/*.war app.war
EXPOSE 8090
ENTRYPOINT exec java -jar app.war

