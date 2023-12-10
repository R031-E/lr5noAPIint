# Dockerfile
FROM openjdk:17

RUN mkdir -p /usr/src/myapp
COPY target/swagger-spring-1.0.0.jar /usr/src/myapp
COPY target/keystore.p12 /usr/src/myapp/target
WORKDIR /usr/src/myapp
# В ENTRYPOINT используйте sh для поддержки переменных окружения
ENTRYPOINT ["sh", "-c", "java -jar -Dspring.config.name=$SPRING_CONFIG_NAME -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE -Dkeystore.path=target/keystore.p12 swagger-spring-1.0.0.jar"]

#RUN java -jar ../swagger-spring-1.0.0.jar