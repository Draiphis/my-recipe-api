FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /workspace

COPY ./my_recipe_api/pom.xml .
RUN mvn dependency:go-offline

COPY ./my_recipe_api/src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /workspace/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]