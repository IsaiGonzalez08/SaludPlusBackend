FROM openjdk:17
ADD target/aqua-qa.jar aqua-qa.jar
ENTRYPOINT ["java", "-jar","aqua-qa.jar"]
