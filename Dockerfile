FROM registry.access.redhat.com/openjdk/openjdk-11-rhel7:1.0-16.1567588131
ENV LC_ALL en_US.UTF-8
ENV LANG en_US.UTF-8
COPY target/coffee-logger.jar coffee-logger.jar
ENTRYPOINT exec java $JAVA_OPTS $OPTIONS -jar coffee-logger.jar