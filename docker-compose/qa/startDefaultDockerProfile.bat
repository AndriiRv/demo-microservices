echo off
cls

echo.
echo - Create ssh-volume docker volume.
call ../createSshVolume.bat

echo.
echo - Start observability and monitoring, kafka and qa env docker containers.
echo.

docker-compose -f docker-compose-observability-and-monitoring.yml -f docker-compose-kafka.yml -f docker-compose.yml up -d

echo.
echo - All docker containers started successfully.
echo.

PAUSE
