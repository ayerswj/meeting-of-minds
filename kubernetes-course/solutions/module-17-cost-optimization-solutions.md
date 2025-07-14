# Solutions: Module 17 - Cost Optimization & Resource Management

## Quiz Answers
1. What is the difference between a request and a limit?
   **Answer:** A request is the minimum resource guaranteed to a container; a limit is the maximum it can use.
2. How can you restrict resource usage in a namespace?
   **Answer:** By applying a ResourceQuota or LimitRange.
3. Name a tool for monitoring Kubernetes costs.
   **Answer:** Kubecost.

## Hands-On Lab Solution
- Set resource requests/limits in a deployment YAML under `resources:`
- Apply a ResourceQuota YAML to a namespace
- Use Kubecost to view cost breakdowns and identify over-provisioned resources

## Case Study Solution
- Analyze cluster usage with Kubecost or similar
- Adjust requests/limits to match actual usage
- Use autoscaling and spot/preemptible nodes to reduce costs