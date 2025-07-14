# Module 01: Introduction to Kubernetes

## Learning Objectives
- Understand what Kubernetes is and why it was created
- Learn about the history and evolution of Kubernetes
- Identify common use cases and the Kubernetes ecosystem
- Recognize alternatives to Kubernetes and when to use it

---

## Lesson

Kubernetes is an open-source platform for automating deployment, scaling, and management of containerized applications. Originally developed by Google, it is now maintained by the Cloud Native Computing Foundation (CNCF).

### History
- Born from Google's internal system called Borg
- Open-sourced in 2014
- Rapidly adopted by the industry

### Use Cases
- Microservices orchestration
- Hybrid and multi-cloud deployments
- Automated scaling and self-healing

### Ecosystem
- Integrates with CI/CD, monitoring, storage, and networking tools
- Supported by all major cloud providers

### Alternatives
- Docker Swarm
- Apache Mesos
- Nomad

---

## Hands-On Lab: Exploring Kubernetes

1. Visit [Kubernetes Playground](https://labs.play-with-k8s.com/)
2. Start a new session and launch a cluster
3. Run `kubectl version` to see the client and server versions
4. Explore the playground UI and try running `kubectl get nodes`

---

## Summary
Kubernetes is the de facto standard for container orchestration, offering powerful features for modern application deployment and management.