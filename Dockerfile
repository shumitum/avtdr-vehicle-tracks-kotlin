FROM amazoncorretto:21-alpine-jdk
COPY build/libs/*.jar avtdr_app.jar
ENTRYPOINT ["java","-jar","/avtdr_app.jar"]