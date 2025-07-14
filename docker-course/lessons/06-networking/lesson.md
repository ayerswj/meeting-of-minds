# Lesson: Docker Networking

## Network Types
- **Bridge:** Default, for single-host container communication
- **Host:** Shares host network stack
- **Overlay:** Multi-host, for Docker Swarm/Kubernetes

## Exposing and Publishing Ports
- `-p <host>:<container>` — Map host port to container port
- `EXPOSE` in Dockerfile (documentation only)

## Container Communication
- Containers on the same bridge network can communicate by name
- Custom networks improve isolation and DNS

---

Continue to the exercises to practice Docker networking.