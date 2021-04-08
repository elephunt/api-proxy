FROM 996692601451.dkr.ecr.us-east-1.amazonaws.com/jdk-11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]