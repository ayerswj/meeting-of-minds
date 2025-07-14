# Lesson: Docker Installation & Setup

## Installing Docker

### Windows & macOS
- Download Docker Desktop from [docker.com](https://www.docker.com/products/docker-desktop)
- Follow the installation wizard
- Requires WSL2 on Windows 10/11

### Linux (Ubuntu example)
```sh
sudo apt-get update
sudo apt-get install \
    ca-certificates \
    curl \
    gnupg
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg
echo \
  "deb [arch="$(dpkg --print-architecture)" signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

## Docker Desktop vs Docker Engine
- **Docker Desktop:** GUI, includes Docker Engine, Compose, and more (Windows/macOS)
- **Docker Engine:** Core runtime, CLI only (Linux)

## Verifying Installation
```sh
docker --version
docker run hello-world
```
- You should see a welcome message if Docker is installed correctly.

---

Continue to the exercises to practice installing and running Docker.