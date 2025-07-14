# Lesson: CI/CD with Docker

## Building Images in CI Pipelines
- Use CI tools (GitHub Actions, GitLab CI, Jenkins) to build Docker images

## Automated Testing with Containers
- Run tests inside containers for consistency
- Use multi-stage builds for test and production

## Pushing to Registries from CI
- Authenticate and push images to Docker Hub or other registries

## Example: GitHub Actions Workflow
```yaml
name: CI
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Build Docker image
        run: docker build -t myapp:latest .
      - name: Run tests
        run: docker run myapp:latest npm test
      - name: Login to Docker Hub
        run: echo ${{ secrets.DOCKER_PASSWORD }} | docker login -u ${{ secrets.DOCKER_USERNAME }} --password-stdin
      - name: Push image
        run: docker push myapp:latest
```

---

Continue to the exercises to practice CI/CD with Docker.