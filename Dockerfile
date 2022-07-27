FROM adoptopenjdk/openjdk11:alpine
CMD ["./mvnw", "clean", "package"]
ARG JAR_FILE_PATH=build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]