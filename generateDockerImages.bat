echo off

set dockerUsername=%1
set microserviceProjectFolderName=%2
set microserviceName=%3
set microserviceTag=%4
set microserviceLogName=%~5

echo.
echo - Start generating of %microserviceLogName% microservice docker image
cd %microserviceProjectFolderName%
call ..\cleanAndBuildGradleProject.bat
docker build . -t %dockerUsername%/%microserviceName%:%microserviceTag%
echo - %microserviceLogName% microservice docker image is successfully generated
cd..
