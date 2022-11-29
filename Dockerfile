FROM openjdk:17
COPY src/main/resources src/main/resources
ADD target/sportify_entity.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
