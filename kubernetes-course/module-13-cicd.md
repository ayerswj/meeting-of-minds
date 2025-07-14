# Module 13: CI/CD with Kubernetes

## Learning Objectives
- Understand CI/CD concepts in Kubernetes
- Use GitOps tools (ArgoCD)
- Explore Jenkins X and Tekton for pipelines
- Automate deployments to Kubernetes

---

## Lesson

### CI/CD in Kubernetes
- Automates build, test, and deployment processes
- Enables rapid, reliable delivery of applications

### GitOps
- Manage infrastructure and deployments via Git
- Tools: ArgoCD, Flux

### ArgoCD
- Declarative GitOps continuous delivery tool
- Syncs Git repositories with Kubernetes clusters

### Jenkins X & Tekton
- Jenkins X: CI/CD for Kubernetes-native applications
- Tekton: Kubernetes-native pipeline framework

### Automated Deployments
- Use webhooks, GitOps, or pipeline triggers

---

## Hands-On Lab: GitOps Deployment

1. Install ArgoCD (see official docs)
2. Connect a Git repository with Kubernetes manifests
3. Deploy an application using ArgoCD
4. Observe automated sync and rollback features

---

## Summary
CI/CD and GitOps practices enable fast, safe, and repeatable deployments in Kubernetes environments.