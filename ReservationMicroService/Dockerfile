FROM openjdk:17
COPY target target
ADD target/ReservationMicroService-0.0.1-SNAPSHOT.jar reservation.jar
ENTRYPOINT ["java","-jar","reservation.jar"]
