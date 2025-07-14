# Solutions: Module 18 - Kubernetes Upgrades & Maintenance

## Quiz Answers
1. What is the difference between cordon and drain?
   **Answer:** Cordon marks a node unschedulable; drain evicts all pods from the node.
2. Why is etcd backup important?
   **Answer:** It allows you to restore cluster state in case of failure or data loss.
3. How do managed Kubernetes services simplify upgrades?
   **Answer:** They automate control plane and node upgrades, reducing manual effort and risk.

## Hands-On Lab Solution
- Use `minikube start --kubernetes-version=<new-version>` to simulate an upgrade
- Use `kubectl drain <node>` and `kubectl uncordon <node>` for node maintenance
- Verify workloads remain available by checking pod status

## Case Study Solution
- Restore etcd backup if upgrade fails
- Roll back node images or versions as needed
- Monitor cluster health and validate recovery