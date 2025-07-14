# Module 16: Troubleshooting & Debugging Kubernetes

## Learning Objectives
- Diagnose and resolve common Kubernetes issues
- Use kubectl and built-in tools for troubleshooting
- Debug pods, nodes, and cluster resources

---

## Lesson

### Common Issues
- Pod CrashLoopBackOff, ImagePullBackOff
- Failed scheduling, resource limits
- Network connectivity problems

### Troubleshooting Tools
- `kubectl describe`, `kubectl logs`, `kubectl exec`
- Events and resource status
- Node and pod metrics

### Debugging Techniques
- Using ephemeral debug containers
- Checking node health and taints
- Analyzing logs and events

---

## Hands-On Lab: Debugging a Broken App
1. Deploy a pod with a deliberate error (e.g., wrong image)
2. Use `kubectl describe pod` and `kubectl logs` to identify the issue
3. Fix the deployment and verify recovery

---

## Quiz
1. What command shows the logs of a pod?
2. How do you check why a pod is not scheduled?
3. What is a CrashLoopBackOff?

---

## Case Study: Production Outage
A team deploys a new version of their app, and all pods enter CrashLoopBackOff. Walk through the steps to diagnose and resolve the issue, including checking logs, events, and reverting to a previous deployment.