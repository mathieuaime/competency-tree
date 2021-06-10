FROM adoptopenjdk/openjdk16:alpine-jre
ARG JAR_FILE=daemon/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
