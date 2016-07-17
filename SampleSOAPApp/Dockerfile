FROM jboss/wildfly:8.2.1.Final
USER jboss
COPY /files/modules/system/layers/base/com /opt/jboss/wildfly/modules/system/layers/base/com/
COPY /files/standalone/configuration/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml
USER root
RUN chown -R jboss.jboss /opt/jboss/wildfly/modules
USER jboss
ADD /target/*.war /opt/jboss/wildfly/standalone/deployments/
RUN /opt/jboss/wildfly/bin/add-user.sh admin Admin#70365 --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

