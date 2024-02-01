echo off

set microserviceProjectFolderName=%1
set microserviceLogName=%~2

echo.
echo - Start generating of %microserviceLogName% microservice docker image with tag as %tagValue%

cd %microserviceProjectFolderName%

call ..\cleanAndBuildGradleProject.bat

echo.
echo - %microserviceLogName% microservice docker image with tag as %tagValue% is successfully generated.

cd..
