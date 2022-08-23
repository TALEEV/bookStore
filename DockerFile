FROM openjdk:11
WORKDIR /opt/app
ARG JAR_FILE=target/bookstore-0.0.1.jar
COPY ${JAR_FILE} bookstore.jar
ENTRYPOINT ["java","-jar","/bookstore.jar"]