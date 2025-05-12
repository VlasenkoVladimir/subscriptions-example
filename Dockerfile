FROM openjdk:17

ARG jarFile=build/libs/subscriptions-example-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${jarFile} /opt/app/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]