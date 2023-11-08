FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/*.jar main.jar
ADD /src/main/docker/opentelemetry-javaagent.jar .
ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar"
CMD ["java", "-jar", "main.jar"]
