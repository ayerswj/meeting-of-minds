# Exercises: Docker Volumes & Storage

1. **Create a Volume:**
   - Run `docker volume create mydata`.

2. **Use a Volume:**
   - Start a container with `-v mydata:/data` and write a file to `/data`.

3. **Persist Data:**
   - Remove the container, start a new one with the same volume, and verify the file is still there.

4. **Backup a Volume:**
   - Use the backup command from the lesson to create a backup tarball.