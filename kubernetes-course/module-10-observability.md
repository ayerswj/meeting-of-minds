# Module 10: Observability: Logging, Monitoring, and Tracing

## Learning Objectives
- Implement logging, monitoring, and tracing in Kubernetes
- Use EFK/ELK for logging
- Use Prometheus and Grafana for monitoring
- Understand distributed tracing basics

---

## Lesson

### Logging
- EFK (Elasticsearch, Fluentd, Kibana) or ELK (Logstash instead of Fluentd)
- Centralizes and visualizes logs from all pods

### Monitoring
- Prometheus: Collects metrics from Kubernetes and applications
- Grafana: Visualizes metrics and dashboards

### Tracing
- Distributed tracing tools (Jaeger, Zipkin)
- Track requests across microservices

---

## Hands-On Lab: Observability Stack

1. Deploy Prometheus and Grafana using Helm
2. Set up EFK/ELK stack (see official Helm charts)
3. Explore logs and metrics in Grafana and Kibana

---

## Summary
Observability is crucial for maintaining, debugging, and scaling Kubernetes applications in production.