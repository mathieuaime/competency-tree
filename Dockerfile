FROM adoptopenjdk/openjdk15:alpine-jre
COPY daemon/target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]