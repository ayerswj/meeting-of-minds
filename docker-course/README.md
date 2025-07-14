# Docker Mastery Curriculum

Welcome to the Docker Mastery Course! This curriculum is designed to take you from a complete beginner to a master Docker user, covering foundational concepts, practical skills, and advanced techniques. Each section includes learning objectives, recommended resources, and hands-on exercises.

---

## How to Use This Curriculum

- Each module has its own folder under `lessons/` with:
  - A lesson guide (`lesson.md`)
  - Hands-on exercises (`exercises.md`)
  - Code examples (`code/`)
  - Quizzes (`quiz.md`)
- Follow the modules in order, complete the exercises, and test your knowledge with quizzes.
- Capstone project instructions are in the `capstone/` folder.

---

## Directory Structure

```
docker-course/
│
├── README.md
├── lessons/
│   ├── 01-introduction/
│   │   ├── lesson.md
│   │   ├── exercises.md
│   │   ├── quiz.md
│   │   └── code/
│   ├── 02-installation/
│   │   └── ...
│   └── ...
├── capstone/
│   ├── instructions.md
│   └── template/
└── resources.md
```

---

## Table of Contents
1. [Introduction to Docker](#1-introduction-to-docker)
2. [Docker Installation & Setup](#2-docker-installation--setup)
3. [Core Docker Concepts](#3-core-docker-concepts)
4. [Working with Docker Images](#4-working-with-docker-images)
5. [Managing Containers](#5-managing-containers)
6. [Docker Networking](#6-docker-networking)
7. [Docker Volumes & Storage](#7-docker-volumes--storage)
8. [Docker Compose](#8-docker-compose)
9. [Docker in Development Workflows](#9-docker-in-development-workflows)
10. [Docker Security Best Practices](#10-docker-security-best-practices)
11. [Advanced Docker: Orchestration with Swarm & Kubernetes](#11-advanced-docker-orchestration-with-swarm--kubernetes)
12. [CI/CD with Docker](#12-cicd-with-docker)
13. [Troubleshooting & Debugging Docker](#13-troubleshooting--debugging-docker)
14. [Docker in Production](#14-docker-in-production)
15. [Capstone Project](#15-capstone-project)

---

## 1. Introduction to Docker
- What is Docker?
- Why use containers?
- Virtual Machines vs Containers
- Docker ecosystem overview

**Exercise:** Research and summarize the main benefits of Docker.

## 2. Docker Installation & Setup
- Installing Docker on Windows, macOS, and Linux
- Docker Desktop vs Docker Engine
- Verifying installation

**Exercise:** Install Docker and run your first container (`hello-world`).

## 3. Core Docker Concepts
- Images, Containers, Registries
- Docker CLI basics
- Docker architecture

**Exercise:** List running containers, images, and understand the Docker daemon.

## 4. Working with Docker Images
- Pulling images from Docker Hub
- Building custom images with Dockerfile
- Tagging and pushing images

**Exercise:** Create a simple Dockerfile for a Node.js or Python app.

## 5. Managing Containers
- Running, stopping, and removing containers
- Container lifecycle
- Inspecting containers
- Logs and resource limits

**Exercise:** Run a web server in a container and access it from your browser.

## 6. Docker Networking
- Bridge, host, and overlay networks
- Exposing and publishing ports
- Container-to-container communication

**Exercise:** Connect two containers using a custom bridge network.

## 7. Docker Volumes & Storage
- Volumes vs bind mounts
- Managing persistent data
- Backing up and restoring volumes

**Exercise:** Use a volume to persist database data.

## 8. Docker Compose
- What is Docker Compose?
- Defining multi-container applications
- Compose commands and lifecycle

**Exercise:** Deploy a multi-container app (e.g., web + database) with Compose.

## 9. Docker in Development Workflows
- Using Docker for local development
- Hot reloading and code sharing
- Environment variables and secrets

**Exercise:** Set up a development environment with Docker Compose.

## 10. Docker Security Best Practices
- Image vulnerabilities
- Least privilege principle
- Secrets management
- Scanning images for vulnerabilities

**Exercise:** Scan an image for vulnerabilities using `docker scan` or Trivy.

## 11. Advanced Docker: Orchestration with Swarm & Kubernetes
- Introduction to orchestration
- Docker Swarm basics
- Kubernetes fundamentals
- Deploying and scaling services

**Exercise:** Deploy a simple app using Docker Swarm and Kubernetes (minikube).

## 12. CI/CD with Docker
- Building images in CI pipelines
- Automated testing with containers
- Pushing to registries from CI

**Exercise:** Set up a GitHub Actions workflow to build and push a Docker image.

## 13. Troubleshooting & Debugging Docker
- Common issues and solutions
- Debugging containers
- Analyzing logs and metrics

**Exercise:** Diagnose and fix a broken containerized app.

## 14. Docker in Production
- Best practices for production deployments
- Monitoring and logging
- Upgrades and rollbacks
- Cost optimization

**Exercise:** Prepare a Dockerized app for production deployment.

## 15. Capstone Project
- Design, build, and deploy a real-world application using Docker
- Document your architecture and choices
- Present your project

---

## Additional Resources
- [Docker Official Documentation](https://docs.docker.com/)
- [Play with Docker](https://labs.play-with-docker.com/)
- [Awesome Docker](https://github.com/veggiemonk/awesome-docker)
- [Docker Curriculum by Prakhar Srivastav](https://docker-curriculum.com/)

---

Happy Dockering!