echo off
cls

set tagValue=s10

call generateDockerImages.bat kent0k accounts accounts %tagValue% Accounts
call generateDockerImages.bat kent0k cards cards %tagValue% Cards
call generateDockerImages.bat kent0k loans loans %tagValue% Loans
call generateDockerImages.bat kent0k configserver configserver %tagValue% "Spring Cloud Config server"
call generateDockerImages.bat kent0k eurekaserver eurekaserver %tagValue% "Spring Cloud Eureka server"
call generateDockerImages.bat kent0k gatewayserver gatewayserver %tagValue% "Spring Cloud Gateway server"

PAUSE
