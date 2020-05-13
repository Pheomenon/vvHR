FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/hr.jar hr.jar
ENTRYPOINT ["java","-jar","/hr.jar", "&"]