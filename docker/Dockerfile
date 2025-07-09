FROM jboss/wildfly:35.0.0.Final

USER root
RUN microdnf install git maven -y

RUN git clone git clone https://github.com/MrAbaddy/SistemaMotoTech.git /opt/app

WORKDIR /opt/app
RUN mvn clean install -DskipTests

RUN cp target/Mototech.war /opt/jboss/wildfly/standalone/deployments/Mototech.war

EXPOSE 8080
VOLUME ["/opt/jboss/wildfly/standalone/log"]
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]