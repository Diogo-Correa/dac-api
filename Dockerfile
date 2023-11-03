# Use uma imagem base do Java. Aqui estamos usando a versão 11 do Java
FROM openjdk:17-slim

RUN apt-get update && apt-get install -y maven

# Variável para armazenar o diretório da aplicação dentro do container
ARG APP_DIR=/usr/app/

# Variável para armazenar o nome do arquivo .jar da aplicação
ARG JAR_FILE=.mvn/wrapper/maven-wrapper.jar

# Cria o diretório da aplicação dentro do container
RUN mkdir -p $APP_DIR

# Define o diretório da aplicação como diretório de trabalho
WORKDIR $APP_DIR

# Copia o arquivo .jar para o diretório da aplicação no container
COPY . $APP_DIR

RUN mvn clean compile
RUN mvn package
# RUN mvn spring-boot:run

# Expõe a porta que a aplicação vai rodar
EXPOSE 8080
# EXPOSE 5432

# Comando para iniciar a aplicação
# ENTRYPOINT ["java", "-jar", "./target/0.0.1-SNAPSHOT.jar"]

ENTRYPOINT [ "mvn", "spring-boot:run" ]