# Solutions: Module 19 - Kubernetes in Production: Best Practices

## Quiz Answers
1. What is a readiness probe?
   **Answer:** A check to determine if a pod is ready to receive traffic.
2. Why are namespaces important in production?
   **Answer:** They provide isolation and resource boundaries for teams and applications.
3. Name two security best practices for Kubernetes.
   **Answer:** Use RBAC and network policies; avoid running containers as root.

## Hands-On Lab Solution
- Add readiness/liveness probes to deployment YAML
- Apply RBAC and network policies for isolation
- Test backup/restore procedures and simulate failures

## Case Study Solution
- Detect incident via monitoring/alerting
- Mitigate by rolling back or fixing misconfiguration
- Conduct postmortem to prevent recurrence