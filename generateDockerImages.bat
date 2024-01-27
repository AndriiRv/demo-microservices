echo off

set microserviceProjectFolderName=%1
set microserviceLogName=%~2

echo.
echo - Start generating of %microserviceLogName% microservice docker image
cd %microserviceProjectFolderName%

call ..\cleanAndBuildGradleProject.bat

echo - %microserviceLogName% microservice docker image is successfully generated
cd..
