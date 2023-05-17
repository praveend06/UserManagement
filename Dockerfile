FROM openjdk:11
COPY ./target/*.jar /target/usermanagement-app.jar
WORKDIR /target
ENTRYPOINT ["java", "-jar", "usermanagement-app.jar"]
