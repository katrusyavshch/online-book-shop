FROM openjdk:17-jdk-alpine AS builder
WORKDIR application
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM openjdk:17-jdk-alpine
WORKDIR /application
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./
ENTRYPOINT ["java", "-cp", "dependencies:spring-boot-loader:snapshot-dependencies:application", "org.springframework.boot.loader.JarLauncher"]


EXPOSE 8080
