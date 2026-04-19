FROM eclipse-temurin:25-jdk

WORKDIR /app

COPY target/tictactoe-1.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]