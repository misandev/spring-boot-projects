FROM openjdk:11
VOLUME /tmp
EXPOSE 8082
ADD ./target/rabbitmq-order-service-producer-0.0.1-SNAPSHOT.jar order-service-producer.jar
ENTRYPOINT ["java","-jar","/order-service-producer.jar"]