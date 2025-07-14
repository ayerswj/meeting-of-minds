# Module 12: Scaling and High Availability

## Learning Objectives
- Use the Horizontal Pod Autoscaler (HPA)
- Understand cluster autoscaling
- Implement high availability patterns

---

## Lesson

### Horizontal Pod Autoscaler (HPA)
- Automatically scales pods based on metrics (CPU, memory)
- `kubectl autoscale deployment <name> --cpu-percent=50 --min=1 --max=10`

### Cluster Autoscaling
- Adds/removes nodes based on resource needs
- Supported by most cloud providers

### High Availability (HA)
- Multiple replicas of control plane and workloads
- Use anti-affinity rules and multiple zones

---

## Hands-On Lab: Autoscaling

1. Enable HPA: `kubectl autoscale deployment nginx --cpu-percent=50 --min=1 --max=5`
2. Simulate load and observe scaling
3. (Cloud) Explore cluster autoscaler settings

---

## Summary
Kubernetes supports both application and infrastructure scaling, enabling resilient and highly available deployments.