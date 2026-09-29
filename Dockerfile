# SET08103 GeoMetrics - Java 21 runtime image.
FROM amazoncorretto:21

WORKDIR /app
COPY target/geometrics.jar geometrics.jar

ENTRYPOINT ["java", "-jar", "geometrics.jar"]
