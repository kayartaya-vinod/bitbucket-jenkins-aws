FROM openjdk:8
MAINTAINER Vinod <vinod@vinod.co>
ADD target/hello-rest-service.jar hello-rest-service.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=4000", "/hello-rest-service.jar"]
EXPOSE 4000