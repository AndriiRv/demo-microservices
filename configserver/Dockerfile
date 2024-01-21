
FROM eclipse-temurin:21-jdk

RUN apt-get update && apt-get install -y \
curl
RUN useradd -ms /bin/bash cnb
USER cnb
WORKDIR /home/cnb

MAINTAINER eazybytes.com

COPY build/libs/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "configserver-0.0.1-SNAPSHOT.jar"]
