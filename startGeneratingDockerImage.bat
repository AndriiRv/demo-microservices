echo off
cls

set tagValue=s11

call generateDockerImages.bat accounts Accounts
call generateDockerImages.bat cards Cards
call generateDockerImages.bat loans Loans
call generateDockerImages.bat configserver "Spring Cloud Config server"
call generateDockerImages.bat eurekaserver "Spring Cloud Eureka server"
call generateDockerImages.bat gatewayserver "Spring Cloud Gateway server"

PAUSE
