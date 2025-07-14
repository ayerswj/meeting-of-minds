# Module 02: Kubernetes Architecture

## Learning Objectives
- Understand the architecture of a Kubernetes cluster
- Identify the roles of master and worker nodes
- Learn about control plane and node components
- Understand how cluster communication works

---

## Lesson

A Kubernetes cluster consists of at least one master node and multiple worker nodes.

### Master Node (Control Plane)
- API Server: Entry point for all REST commands
- etcd: Key-value store for cluster data
- Controller Manager: Governs controllers
- Scheduler: Assigns workloads to nodes

### Worker Node
- Kubelet: Ensures containers are running
- Kube Proxy: Handles networking
- Container Runtime: Runs containers (e.g., Docker, containerd)

### Cluster Communication
- All components communicate via the API server
- etcd stores the cluster state

---

## Hands-On Lab: Visualizing Cluster Architecture

1. Use [Kubernetes Interactive Tutorial](https://kubernetes.io/docs/tutorials/kubernetes-basics/) Module 2
2. Observe the control plane and worker node roles
3. Run `kubectl get nodes` and `kubectl describe node <node-name>`

---

## Summary
Kubernetes architecture separates management (control plane) from workload execution (worker nodes), enabling scalable and resilient deployments.