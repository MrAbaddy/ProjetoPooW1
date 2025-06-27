
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

RUN git clone https://github.com/seu-usuario/SistemaMotoTech.git .

RUN mvn clean install -DskipTests

FROM jboss/wildfly:26.1.3.Final

COPY --from=builder /app/target/*.war /opt/jboss/wildfly/standalone/deployments/

EXPOSE 8080

VOLUME ["/opt/jboss/wildfly/standalone/log"]

ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
