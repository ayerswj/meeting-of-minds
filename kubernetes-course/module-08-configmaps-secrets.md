# Module 08: ConfigMaps, Secrets, and Environment Management

## Learning Objectives
- Use ConfigMaps and Secrets for configuration management
- Inject environment variables into pods
- Apply best practices for managing sensitive data

---

## Lesson

### ConfigMaps
- Store non-sensitive configuration data
- Mounted as files or environment variables

### Secrets
- Store sensitive data (passwords, tokens)
- Base64-encoded, not encrypted by default

### Environment Variables
- Pass configuration to containers
- Use `env` and `envFrom` in pod specs

### Best Practices
- Use RBAC to restrict access
- Avoid hardcoding secrets in images or manifests

---

## Hands-On Lab: Managing Config and Secrets

1. Create a ConfigMap: `kubectl create configmap my-config --from-literal=key1=value1`
2. Create a Secret: `kubectl create secret generic my-secret --from-literal=password=secretpass`
3. Deploy a pod that uses both as environment variables

---

## Summary
ConfigMaps and Secrets enable secure and flexible configuration management for Kubernetes applications.