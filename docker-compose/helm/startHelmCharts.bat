echo off
cls

@REM Before work with Helm is need to add absolute path where Helm executable (.exe) file located to Path of Windows OS.

@REM helm dependencies build --- is using for build chart (is need to be located inside chart folder. I.e. on the same level with Chart.yaml)

echo.
echo - Remove all Helm charts and Persistent Volumes (pvc).
helm uninstall keycloak
helm uninstall kafka
helm uninstall prometheus
helm uninstall loki
helm uninstall tempo
helm uninstall grafana
helm uninstall mysql-accounts
helm uninstall mysql-cards
helm uninstall mysql-loans
helm uninstall dev-env

kubectl delete pvc grafana
kubectl delete pvc data-tempo-grafana-tempo-ingester-0
kubectl delete pvc data-loki-grafana-loki-querier-0
kubectl delete pvc data-loki-grafana-loki-ingester-0
kubectl delete pvc loki-grafana-loki-compactor
kubectl delete pvc data-keycloak-postgresql-0
kubectl delete pvc data-kafka-controller-0
kubectl delete pvc data-mysql-accounts-0
kubectl delete pvc data-mysql-cards-0
kubectl delete pvc data-mysql-loans-0

echo.
echo - Install all Helm charts to Kubernetes.
helm install keycloak keycloak
helm install kafka kafka
helm install prometheus kube-prometheus
helm install loki grafana-loki
helm install tempo grafana-tempo
helm install grafana grafana
helm install mysql-accounts mysql-accounts
helm install mysql-cards mysql-cards
helm install mysql-loans mysql-loans
helm install dev-env environments/dev-env

PAUSE
