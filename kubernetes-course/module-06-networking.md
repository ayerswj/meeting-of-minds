# Module 06: Networking in Kubernetes

## Learning Objectives
- Understand the Kubernetes networking model
- Use Services (ClusterIP, NodePort, LoadBalancer)
- Configure Ingress controllers and resources
- Apply Network Policies

---

## Lesson

### Networking Model
- Every pod gets its own IP address
- Flat network space: all pods can communicate by default

### Services
- ClusterIP: Internal access
- NodePort: Exposes service on each node's IP
- LoadBalancer: Integrates with cloud load balancers

### Ingress
- Manages external access to services
- Supports routing, SSL termination, and more

### Network Policies
- Control traffic flow at the IP address or port level
- Used for security and isolation

---

## Hands-On Lab: Exposing and Securing Services

1. Deploy an app: `kubectl create deployment web --image=nginx`
2. Expose it: `kubectl expose deployment web --type=NodePort --port=80`
3. (Optional) Install an Ingress controller (see docs)
4. Apply a simple NetworkPolicy (see docs for YAML example)

---

## Summary
Kubernetes networking enables flexible service exposure and security controls, supporting both internal and external access patterns.