FROM openjdk:11
ENV OPTIONS -Dspring.profiles.active=prod
COPY target/coffee-logger.jar coffee-logger.jar
COPY bot_token.txt bot_token.txt
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "coffee-logger.jar"]