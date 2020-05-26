FROM frolvlad/alpine-java:jdk8-slim
VOLUME /tmp
ADD service-authentication-0.0.1.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]