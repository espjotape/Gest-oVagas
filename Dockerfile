# Usa a imagem oficial do Ubuntu mais recente como base para a fase de build
FROM ubuntu:latest AS build
 
# Atualiza os pacotes do sistema 
RUN apt-get update
# Instala o OpenJDK 17 para compilar e rodar o projeto Java  
RUN apt-get install openjdk-17-jdk -y
# Copia todos os arquivos do projeto para dentro do container  
COPY . .

# Instala o Maven para gerenciar dependências e compilar o projeto  
RUN apt-get install maven -y
# Limpa e compila o projeto usando Maven, gerando o arquivo JAR  
RUN mvn clean install

# Usa uma imagem mais leve do OpenJDK 17 para rodar a aplicação  
FROM openjdk:17-jdk-slim
# Expõe a porta 8080 para permitir acesso à aplicação  
EXPOSE 8080

# Copia o JAR gerado na fase de build para a fase final do container  
COPY --from=build /target/gestao_vagas-0.0.1.jar app.jar

# Define o comando que será executado ao iniciar o container  
ENTRYPOINT [ "java", "-jar", "app.jar" ]