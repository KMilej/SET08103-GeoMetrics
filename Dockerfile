##FROM amazoncorretto:27
##COPY ./target/classes/com /tmp/com
##WORKDIR /tmp
##ENTRYPOINT ["java", "com.napier.sem.Main"]
#
#FROM amazoncorretto:27
#COPY ./target/semApp.jar /tmp
#WORKDIR /tmp
#ENTRYPOINT ["java", "-jar", "semApp.jar"]

FROM amazoncorretto:27
COPY ./target/semApp-0.1.0.2.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "semApp-0.1.0.2.jar"]