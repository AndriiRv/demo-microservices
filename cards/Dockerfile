#To make local docker image: docker build . -t <docker_username>/<name_of_docker_image>:<name_of_tag>
#To create docker container from docker image: docker run -p <external_ip_number>:<api_ip_number> <docker_username>/<name_of_docker_image>:<name_of_tag>
#or by using of docker compose.

#To publish docker image to remote DockerHub related to docker account username: docker image push docker.io/<docker_username>/<name_of_docker_image>:<name_of_tag>

FROM eclipse-temurin:21-jdk

MAINTAINER eazybytes.com

COPY build/libs/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "cards-0.0.1-SNAPSHOT.jar"]
