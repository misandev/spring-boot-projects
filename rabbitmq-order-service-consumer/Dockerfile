FROM openjdk:11
VOLUME /tmp
EXPOSE 8085
ADD ./target/rabbitmq-order-service-consumer-0.0.1-SNAPSHOT.jar order-service-consumer.jar
ENTRYPOINT ["java","-jar","/order-service-consumer.jar"]