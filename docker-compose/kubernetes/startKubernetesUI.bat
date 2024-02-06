echo off
cls

@REM Kubernetes with single-node cluster can be started via Settings in Docker Desktop.

echo - Deploying the Dashboard UI.
kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.7.0/aio/deploy/recommended.yaml

echo.
echo - Creating Service Account with the name 'admin-user' in namespace 'kubernetes-dashboard'.
kubectl apply -f dashboard-adminuser.yml

echo.
echo - Grant admins privileges for admin user.
kubectl apply -f dashboard-rolebinding.yml

@REM Commented below commands because we are using Helm charts instead.

@REM echo.
@REM echo - Add microservice manifests and configmaps.yml
@REM kubectl apply -f ms_manifests/keycloak.yml -f configmaps.yml -f ms_manifests/configserver.yml -f ms_manifests/eurekaserver.yml -f ms_manifests/accounts.yml -f ms_manifests/loans.yml -f ms_manifests/cards.yml -f ms_manifests/gatewayserver.yml

echo.
echo - Create ssh-volume docker volume.
call ../createSshVolume.bat

echo.
echo - Fetch bearer token to use during login Kubernetes UI.
echo - Bearer token should be in clipboard already. So just put it into token field in Kubernetes UI.
kubectl -n kubernetes-dashboard create token admin-user | clip

echo.
echo - Should be opened Kubernetes UI login page (http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login)
echo - in the default browser and will run Kubernetes UI.
start "" http://localhost:8001/api/v1/namespaces/kubernetes-dashboard/services/https:kubernetes-dashboard:/proxy/#/login
kubectl proxy
