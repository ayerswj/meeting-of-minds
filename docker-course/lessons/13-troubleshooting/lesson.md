# Lesson: Troubleshooting & Debugging Docker

## Common Issues
- Container won't start (image, config, port conflicts)
- Network or volume errors

## Debugging Containers
- Use `docker logs <container>` for output
- Use `docker exec -it <container> /bin/sh` to get a shell inside
- Inspect with `docker inspect <container>`

## Analyzing Logs and Metrics
- `docker logs` for container logs
- `docker stats` for resource usage
- Use monitoring tools (Prometheus, Grafana)

---

Continue to the exercises to practice troubleshooting Docker.