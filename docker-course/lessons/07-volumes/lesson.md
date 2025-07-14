# Lesson: Docker Volumes & Storage

## Volumes vs Bind Mounts
- **Volumes:** Managed by Docker, stored in Docker's directory
- **Bind Mounts:** Link to a specific location on the host

## Managing Persistent Data
- Use `-v <volume>:/path` to mount a volume
- Data in volumes persists after container removal

## Backing Up and Restoring Volumes
- Use `docker run --rm -v <volume>:/data -v $(pwd):/backup busybox tar cvf /backup/backup.tar /data` to back up
- Use `docker run --rm -v <volume>:/data -v $(pwd):/backup busybox tar xvf /backup/backup.tar -C /data` to restore

---

Continue to the exercises to practice using Docker volumes.