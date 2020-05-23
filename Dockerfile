FROM java:8-jdk-alpine

COPY ./target/metalprice.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch metalprice.jar'

ENTRYPOINT ["java","-jar","metalprice.jar"]