# Module 20: Multi-Tenancy & Policy Management

## Learning Objectives
- Implement multi-tenancy in Kubernetes
- Use policies for access and resource control
- Apply tools for policy enforcement

---

## Lesson

### Multi-Tenancy Approaches
- Namespaces and RBAC
- Network segmentation
- Resource quotas and isolation

### Policy Management
- OPA/Gatekeeper for policy enforcement
- PodSecurityPolicies (deprecated), alternatives

---

## Hands-On Lab: Namespace Isolation
1. Create multiple namespaces for different teams
2. Apply RBAC and ResourceQuotas
3. Enforce policies with OPA/Gatekeeper

---

## Quiz
1. How does RBAC help with multi-tenancy?
2. What is OPA used for?
3. How can you limit resource usage per team?

---

## Case Study: Policy Violation
A team accidentally deploys a privileged pod. Describe how policies can prevent this and how to audit for violations.