FROM amazoncorretto:23
COPY ./target/semApp-0.1.0.2.jar /tmp/semApp-0.1.0.2.jar
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semApp-0.1.0.2.jar"]
