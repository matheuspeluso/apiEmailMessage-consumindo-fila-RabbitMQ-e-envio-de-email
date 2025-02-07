# Etapa 1: Build
FROM maven:3.9.4-eclipse-temurin-21 AS builder
WORKDIR /app

# Copia o arquivo de dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia o código-fonte e compila o projeto
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem de execução
FROM openjdk:21-jdk-slim
WORKDIR /app

# Copia o JAR correto (ajuste o nome conforme seu pom.xml)
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta
EXPOSE 9001

# Executa a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]