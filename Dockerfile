FROM maven:3.8.3-openjdk-17  AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -Dmaven.test.skip=true -Dmaven.repo.local=.m2/repository

FROM openjdk:17-oracle
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]