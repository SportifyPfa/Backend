FROM openjdk:17
COPY target target
ADD target/authentification-0.0.1-SNAPSHOT.jar user.jar
ENTRYPOINT ["java","-jar","user.jar"]