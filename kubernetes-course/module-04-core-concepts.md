# Module 04: Core Concepts

## Learning Objectives
- Understand Pods, ReplicaSets, and Deployments
- Learn about Namespaces and Services
- Use Labels and Selectors for resource management

---

## Lesson

### Pods
- The smallest deployable unit in Kubernetes
- Can contain one or more containers

### ReplicaSets
- Ensure a specified number of pod replicas are running
- Replace failed pods automatically

### Deployments
- Manage ReplicaSets and provide declarative updates
- Enable rolling updates and rollbacks

### Namespaces
- Provide logical separation within a cluster
- Useful for multi-team or multi-project environments

### Services
- Expose pods to network traffic
- Types: ClusterIP, NodePort, LoadBalancer

### Labels and Selectors
- Labels: Key-value pairs attached to resources
- Selectors: Used to filter and group resources

---

## Hands-On Lab: Deploy Your First App

1. Create a deployment: `kubectl create deployment hello-node --image=k8s.gcr.io/echoserver:1.4`
2. Expose it as a service: `kubectl expose deployment hello-node --type=LoadBalancer --port=8080`
3. List pods, services, and deployments: `kubectl get pods,svc,deployments`
4. Clean up: `kubectl delete service hello-node && kubectl delete deployment hello-node`

---

## Summary
Understanding these core concepts is essential for working with Kubernetes workloads and managing applications effectively.