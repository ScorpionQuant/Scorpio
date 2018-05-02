FROM openjdk:8-jdk-alpine 
EXPOSE 8080
VOLUME /tmp
ADD app/build/libs/app-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
