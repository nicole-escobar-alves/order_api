FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml ./

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-slim

RUN apt-get update && apt-get install -y postgresql-client

ENV DB_HOST =""
ENV DB_PORT =""
ENV DB_NAME =""
ENV DB_USER =""
ENV DB_PASSWORD =""
ENV PAYMENT_NAME =""
ENV PAYMENT_URL =""
ENV PRODUCTION_NAME =""
ENV PRODUCTION_URL =""

WORKDIR /app
COPY --from=builder ./app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar",\
    "--spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}",\
    "--spring.datasource.username=${DB_USER}",\
    "--spring.datasource.password=${DB_PASSWORD}",\
    "--payment-service.name=${PAYMENT_NAME}",\
    "--payment-service.url=${PAYMENT_URL}",\
    "--production-service.name=${PRODUCTION_NAME}",\
    "--production-service.url=${PRODUCTION_URL}"]

EXPOSE 8080