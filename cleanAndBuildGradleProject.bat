echo off

echo - Clean, build and perform jibDockerBuild %microserviceLogName% microservice Gradle project
gradlew clean build jibDockerBuild
