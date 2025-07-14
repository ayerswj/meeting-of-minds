# Lesson: Docker in Development Workflows

## Using Docker for Local Development
- Consistent environments for all developers
- Avoids "works on my machine" issues

## Hot Reloading and Code Sharing
- Use bind mounts to share code between host and container
- Example: `-v $(pwd):/app` for live code updates

## Environment Variables and Secrets
- Use `-e VAR=value` or `.env` files
- Avoid hardcoding secrets in images
- Use Docker secrets for sensitive data (Swarm/K8s)

---

Continue to the exercises to practice Docker in development.