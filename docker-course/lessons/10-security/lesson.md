# Lesson: Docker Security Best Practices

## Image Vulnerabilities
- Use official images when possible
- Regularly update images

## Least Privilege Principle
- Run containers as non-root users
- Limit container capabilities

## Secrets Management
- Use environment variables or Docker secrets (Swarm/K8s)
- Never store secrets in images or code

## Scanning Images
- Use `docker scan` or tools like Trivy to check for vulnerabilities

---

Continue to the exercises to practice Docker security.