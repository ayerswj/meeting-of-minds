# Module 18: Kubernetes Upgrades & Maintenance

## Learning Objectives
- Plan and perform safe Kubernetes upgrades
- Maintain cluster health and reliability
- Use maintenance best practices

---

## Lesson

### Upgrade Strategies
- Control plane vs. node upgrades
- Version compatibility and planning
- Using managed services for upgrades

### Maintenance Tasks
- Backups (etcd, manifests)
- Draining and cordoning nodes
- Monitoring cluster health

---

## Hands-On Lab: Cluster Upgrade Simulation
1. Simulate a minor version upgrade with Minikube or Kind
2. Practice draining and upgrading nodes
3. Verify workloads remain available

---

## Quiz
1. What is the difference between cordon and drain?
2. Why is etcd backup important?
3. How do managed Kubernetes services simplify upgrades?

---

## Case Study: Upgrade Gone Wrong
A cluster upgrade causes downtime. Outline steps to recover, including restoring from backup and rolling back nodes.