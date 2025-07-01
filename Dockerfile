# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Argumento do repositório Git
ARG GIT_REPO=git clone https://github.com/MrAbaddy/SistemaMotoTech.git

# Clona o projeto
RUN git clone $GIT_REPO /app

# Entra na pasta do projeto
WORKDIR /app

# Compila o projeto (gera o .war)
RUN mvn clean package -DskipTests

# Etapa de runtime com WildFly
FROM quay.io/wildfly/wildfly:36.0.0.Final

# Diretório onde o WildFly espera o .war (deployment automático)
COPY --from=builder /app/target/Mototech-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/app.war

# Cria diretório de logs e torna volume
RUN mkdir -p /opt/jboss/wildfly/standalone/log && \
    chown -R jboss:jboss /opt/jboss/wildfly/standalone/log

VOLUME ["/opt/jboss/wildfly/standalone/log"]

# Expõe a porta HTTP do WildFly
EXPOSE 8080

# ENTRYPOINT padrão do WildFly
ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
