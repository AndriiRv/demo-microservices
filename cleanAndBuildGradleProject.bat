echo off

echo - Clean, build and perform jibDockerBuild %microserviceLogName% on microservice Gradle project
gradlew clean build jibDockerBuild
