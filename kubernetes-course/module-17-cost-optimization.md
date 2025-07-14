# Module 17: Cost Optimization & Resource Management

## Learning Objectives
- Optimize Kubernetes resource usage and costs
- Use resource requests, limits, and quotas
- Monitor and analyze cluster costs

---

## Lesson

### Resource Management
- Requests and limits for CPU/memory
- ResourceQuotas and LimitRanges
- Rightsizing workloads

### Cost Optimization
- Node sizing and autoscaling
- Spot/preemptible nodes
- Monitoring with cost tools (e.g., Kubecost)

---

## Hands-On Lab: Rightsizing Workloads
1. Set resource requests and limits for a deployment
2. Apply a ResourceQuota to a namespace
3. Use Kubecost (or similar) to analyze costs

---

## Quiz
1. What is the difference between a request and a limit?
2. How can you restrict resource usage in a namespace?
3. Name a tool for monitoring Kubernetes costs.

---

## Case Study: Reducing Cloud Spend
A company notices high cloud bills due to over-provisioned clusters. Describe steps to analyze and optimize resource usage, including adjusting requests/limits and using autoscaling.