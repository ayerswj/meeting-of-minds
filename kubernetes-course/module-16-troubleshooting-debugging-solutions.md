# Solutions: Module 16 - Troubleshooting & Debugging Kubernetes

## Quiz Answers
1. What command shows the logs of a pod?  
   **Answer:** `kubectl logs <pod-name>`
2. How do you check why a pod is not scheduled?  
   **Answer:** `kubectl describe pod <pod-name>` or check events for scheduling errors.
3. What is a CrashLoopBackOff?  
   **Answer:** It means a pod is repeatedly crashing and restarting due to an error in the container or application.

## Hands-On Lab Solution
- Deploy a pod with a wrong image: `kubectl run test --image=wrongimage`
- Use `kubectl describe pod test` to see image pull errors.
- Use `kubectl logs test` if the pod starts and then fails.
- Fix by updating the deployment with the correct image: `kubectl set image deployment/test test=nginx`

## Case Study Solution
- Check pod status with `kubectl get pods` and `kubectl describe pod <name>`
- Review logs with `kubectl logs <name>`
- Identify the error (e.g., bad image, missing config)
- Roll back to a previous deployment if needed: `kubectl rollout undo deployment/<name>`
- Monitor until pods are healthy.