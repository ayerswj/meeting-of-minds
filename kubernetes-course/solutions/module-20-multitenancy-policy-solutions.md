# Solutions: Module 20 - Multi-Tenancy & Policy Management

## Quiz Answers
1. How does RBAC help with multi-tenancy?
   **Answer:** It restricts access to resources based on user roles and namespaces.
2. What is OPA used for?
   **Answer:** Open Policy Agent (OPA) enforces custom policies in Kubernetes.
3. How can you limit resource usage per team?
   **Answer:** By applying ResourceQuotas and LimitRanges to namespaces.

## Hands-On Lab Solution
- Create namespaces: `kubectl create namespace team-a`
- Apply RBAC and ResourceQuota YAMLs to each namespace
- Install and configure OPA/Gatekeeper for policy enforcement

## Case Study Solution
- Policies (e.g., OPA) can block privileged pods
- Audit logs and policy reports help detect violations