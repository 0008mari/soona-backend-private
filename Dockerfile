FROM adoptopenjdk/openjdk11:alpine
RUN apk add tzdata  \
    && cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime  \
    && echo "Asia/Seoul" > /etc/timezone
CMD ["./mvnw", "clean", "package"]
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
