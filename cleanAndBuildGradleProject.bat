echo off

echo - Clean, build and perform jibDockerBuild of %microserviceLogName% microservice Gradle project
gradlew clean build jibDockerBuild
