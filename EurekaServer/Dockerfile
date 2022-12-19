FROM openjdk:17
COPY target target
ADD target/EurekaServer-0.0.1-SNAPSHOT.jar server.jar
ENTRYPOINT ["java","-jar","server.jar"]
