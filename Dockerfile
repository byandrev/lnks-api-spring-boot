FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/lnks.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app.jar"]