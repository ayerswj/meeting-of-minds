# Module 07: Storage in Kubernetes

## Learning Objectives
- Understand Volumes and Persistent Volumes
- Use Persistent Volume Claims and Storage Classes
- Deploy StatefulSets for stateful applications

---

## Lesson

### Volumes
- Provide storage for pods
- Ephemeral by default (lost when pod is deleted)

### Persistent Volumes (PV)
- Cluster-wide storage resources
- Provisioned by admins or dynamically

### Persistent Volume Claims (PVC)
- Requests for storage by users
- Binds to available PVs

### Storage Classes
- Define types of storage (e.g., SSD, HDD)
- Enable dynamic provisioning

### StatefulSets
- Manage stateful applications
- Provide stable network identity and storage

---

## Hands-On Lab: Using Persistent Storage

1. Create a PersistentVolume and PersistentVolumeClaim (see docs for YAML example)
2. Deploy a pod using the PVC
3. Scale a StatefulSet and observe storage behavior

---

## Summary
Kubernetes provides robust storage primitives for both stateless and stateful applications, supporting dynamic and persistent storage needs.