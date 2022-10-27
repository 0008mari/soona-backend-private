FROM adoptopenjdk/openjdk11:alpine
CMD ["./mvnw", "clean", "package"]
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
