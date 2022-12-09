FROM openjdk:17
COPY target target
ADD target/sportify_entity.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
