FROM openjdk:17
COPY target target
ADD target/GatewayApi-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java","-jar","gateway.jar"]

