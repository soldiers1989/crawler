# This image is based on Alpine Linux image, which is only a 5MB image, and contains OracleJDK 8.
FROM 10.167.211.250/crawler/standalone-chrome-debug:zh-net
#FROM selenium/standalone-chrome-debug
# VOLUME /tmp
EXPOSE 5050
ADD common-microservice-housingfund-quanzhou-0.0.1-SNAPSHOT.jar app.jar
ADD entry_point.sh /run.sh
RUN chmod a+x /run.sh
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT ["/run.sh"]