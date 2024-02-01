echo off
cls

echo.
echo - Start observability and monitoring, kafka and default env docker containers.
echo.

docker-compose -f docker-compose-observability-and-monitoring.yml -f docker-compose-kafka.yml -f docker-compose.yml up -d

echo.
echo - All docker containers started successfully.
echo.

PAUSE
