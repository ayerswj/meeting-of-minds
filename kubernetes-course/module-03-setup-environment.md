# Module 03: Setting Up Your Environment

## Learning Objectives
- Install and configure a local Kubernetes cluster
- Understand the use of Minikube, Kind, and cloud providers
- Learn kubectl basics
- Launch your first Kubernetes cluster

---

## Lesson

There are several ways to set up a Kubernetes cluster for learning and development:

### Local Options
- **Minikube**: Runs a single-node Kubernetes cluster locally
- **Kind**: Runs Kubernetes clusters in Docker containers

### Cloud Providers
- Google Kubernetes Engine (GKE)
- Amazon Elastic Kubernetes Service (EKS)
- Azure Kubernetes Service (AKS)

### kubectl
- The command-line tool to interact with Kubernetes clusters
- Install via package manager or from the official site

---

## Hands-On Lab: Launch Your First Cluster

1. Install Minikube: https://minikube.sigs.k8s.io/docs/start/
2. Start a cluster: `minikube start`
3. Verify: `kubectl get nodes`
4. Try stopping and starting the cluster: `minikube stop` and `minikube start`

---

## Summary
Setting up a local or cloud-based Kubernetes cluster is the first step to hands-on learning. kubectl is your primary tool for cluster management.