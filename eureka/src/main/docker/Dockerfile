FROM frolvlad/alpine-java:jdk8-slim
VOLUME /tmp
ADD eureka-0.0.1.jar eureka.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]