# Module 05: Deploying Applications

## Learning Objectives
- Create and manage Deployments
- Perform rolling updates and rollbacks
- Manage the application lifecycle in Kubernetes

---

## Lesson

### Deployments
- Declarative way to manage application updates
- Ensures the desired state is maintained

### Rolling Updates
- Update applications with zero downtime
- `kubectl rollout status deployment/<name>` to check progress

### Rollbacks
- Revert to previous versions if issues occur
- `kubectl rollout undo deployment/<name>`

### Application Lifecycle
- Scale up/down: `kubectl scale deployment <name> --replicas=<n>`
- Pause/resume: `kubectl rollout pause/resume deployment/<name>`

---

## Hands-On Lab: Rolling Update and Rollback

1. Create a deployment: `kubectl create deployment nginx --image=nginx:1.14.2`
2. Update the deployment: `kubectl set image deployment/nginx nginx=nginx:1.16.1`
3. Check rollout status: `kubectl rollout status deployment/nginx`
4. Rollback: `kubectl rollout undo deployment/nginx`

---

## Summary
Deployments provide robust mechanisms for managing application updates and rollbacks, ensuring high availability and reliability.