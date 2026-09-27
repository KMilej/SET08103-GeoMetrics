FROM amazoncorretto:23
COPY ./target/semApp.jar /tmp/semApp.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semApp.jar"]