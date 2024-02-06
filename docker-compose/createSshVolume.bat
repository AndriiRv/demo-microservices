echo off

echo.
echo - FYI we need manually add ssh keys from host OS to 'ssh_keys' folder to pass host ssh keys to local docker container to assume that 'GIT' profile of Spring cloud config will work.

echo.
echo - Stop and remove 'dummy-container' old docker container which is using to access to old 'ssh-volume' docker volume which will be removed also.
docker stop dummy-container
docker volume rm ssh-volume

echo.
echo - Copy ssh keys to 'ssh-volume' docker volume.
docker run -d --rm --name dummy-container -v ssh-volume:/root alpine tail -f /dev/null
docker cp ../ssh_keys/id_ed25519 dummy-container:/root
docker cp ../ssh_keys/id_ed25519.pub dummy-container:/root
docker cp ../ssh_keys/known_hosts dummy-container:/root
docker stop dummy-container

echo.
echo - 'ssh-volume' docker volume created successfully.

echo.
echo - FYI 'ssh-volume' docker volume can be accessed from '\\wsl.localhost\docker-desktop-data\version-pack-data\community\docker\volumes\ssh-volume\_data'.
