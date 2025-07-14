# Module 11: Security in Kubernetes

## Learning Objectives
- Implement RBAC and authentication
- Apply network and pod security policies
- Manage secrets securely

---

## Lesson

### RBAC (Role-Based Access Control)
- Controls access to resources based on roles
- Define Roles, RoleBindings, ClusterRoles, ClusterRoleBindings

### Authentication
- Users, service accounts, and authentication plugins

### Network Policies
- Restrict traffic between pods and namespaces

### Pod Security Policies (PSP)
- Define security controls for pod creation
- (Note: PSP is deprecated; use alternatives like OPA/Gatekeeper)

### Secrets Management
- Use Kubernetes Secrets for sensitive data
- Integrate with external secret stores (e.g., HashiCorp Vault)

---

## Hands-On Lab: Securing Your Cluster

1. Create a Role and RoleBinding for limited access
2. Apply a NetworkPolicy to restrict pod communication
3. Store and use a secret in a pod

---

## Summary
Security is a critical aspect of Kubernetes, involving access control, network segmentation, and secure secret management.