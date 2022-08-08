FROM eclipse-temurin:17-jdk-focal
WORKDIR /app
COPY build/libs/productservice-0.0.1-SNAPSHOT-plain.jar productservice-0.0.1-SNAPSHOT-plain.jar
ENTRYPOINT ["java","-jar","/app/productservice-0.0.1-SNAPSHOT-plain.jar"]