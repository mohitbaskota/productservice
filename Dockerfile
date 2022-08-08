FROM eclipse-temurin:17-jdk-focal
COPY build/libs/productservice-0.0.1-SNAPSHOT.jar productservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/productservice-0.0.1-SNAPSHOT.jar"]