# Lesson: Docker Compose

## What is Docker Compose?
- Tool for defining and running multi-container Docker applications
- Uses `docker-compose.yml` file

## Defining Multi-Container Apps
- Services, networks, and volumes defined in YAML
- Example:
```yaml
version: '3'
services:
  web:
    image: nginx
    ports:
      - "8080:80"
  db:
    image: postgres
```

## Compose Commands
- `docker compose up` — Start all services
- `docker compose down` — Stop and remove services
- `docker compose ps` — List running services

## Lifecycle
- Build → Up → Down → Logs → Scale

---

Continue to the exercises to practice Docker Compose.