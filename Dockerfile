FROM maven:3.9.6-eclipse-temurin-21 AS builder

ARG GIT_REPO=https://github.com/MrAbaddy/ProjetoPooW1

RUN git clone $GIT_REPO /app

WORKDIR /app

RUN mvn clean package -DskipTests

FROM jboss/wildfly:latest

COPY --from=builder /app/target/Mototech-1.0-SNAPSHOT.war /opt/jboss/wildfly/standalone/deployments/app.war

RUN mkdir -p /opt/jboss/wildfly/standalone/log && \
    chown -R jboss:jboss /opt/jboss/wildfly/standalone/log

VOLUME ["/opt/jboss/wildfly/standalone/log"]

EXPOSE 8080

ENTRYPOINT ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]