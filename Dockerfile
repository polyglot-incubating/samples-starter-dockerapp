FROM: java:8
EXPOSE 8080
ADD /target/samples-starter-dockerapp.jar samples-starter-dockerapp.jar
ENTRYPOINT ["java", "-jar", "samples-starter-dockerapp.jar"]