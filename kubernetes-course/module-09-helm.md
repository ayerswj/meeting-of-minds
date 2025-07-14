# Module 09: Helm and Package Management

## Learning Objectives
- Understand what Helm is and why it's used
- Install and use Helm to manage applications
- Create and manage Helm charts
- Apply best practices for Helm usage

---

## Lesson

### What is Helm?
- The package manager for Kubernetes
- Simplifies deployment and management of applications

### Installing Helm
- Download from https://helm.sh/
- Install via package manager (e.g., Homebrew, apt)

### Using Helm
- Add repositories: `helm repo add stable https://charts.helm.sh/stable`
- Install a chart: `helm install my-release stable/nginx`
- Upgrade/rollback: `helm upgrade`, `helm rollback`

### Creating Charts
- Scaffold a new chart: `helm create mychart`
- Customize templates and values

### Best Practices
- Use values.yaml for configuration
- Version your charts

---

## Hands-On Lab: Deploy with Helm

1. Install Helm
2. Add a repository and install a sample chart
3. Inspect the release: `helm list`, `helm status <release>`
4. Uninstall: `helm uninstall <release>`

---

## Summary
Helm streamlines application deployment and management, making it easier to handle complex Kubernetes workloads.