FROM openjdk:17
ADD target/sportify_entity.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
