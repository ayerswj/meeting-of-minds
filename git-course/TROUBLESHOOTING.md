# Git Troubleshooting Guide

A comprehensive guide to diagnosing and resolving common Git issues and problems.

## 🚨 Emergency Recovery

### Lost Commits
```bash
# Find lost commits in reflog
git reflog

# Recover specific commit
git checkout <commit-hash>

# Create branch from lost commit
git checkout -b recovery-branch <commit-hash>

# Reset to lost commit
git reset --hard <commit-hash>
```

### Deleted Branch Recovery
```bash
# Find deleted branch in reflog
git reflog --all | grep "branch-name"

# Recreate branch from reflog
git checkout -b branch-name <commit-hash>

# Alternative: use git fsck
git fsck --lost-found
```

### Corrupted Repository
```bash
# Check repository integrity
git fsck --full

# Recover corrupted objects
git fsck --lost-found

# Rebuild index
rm .git/index
git reset

# Rebuild refs
git update-ref -d refs/heads/broken-branch
```

## 🔧 Common Issues

### Merge Conflicts

**Problem**: Git stops during merge with conflict messages.

**Solution**:
```bash
# 1. Check which files have conflicts
git status

# 2. Open conflicted files and resolve manually
# Look for conflict markers: <<<<<<< HEAD, =======, >>>>>>>

# 3. Stage resolved files
git add resolved-file.txt

# 4. Complete the merge
git commit
# or
git merge --continue

# 5. If you want to abort
git merge --abort
```

**Prevention**:
- Communicate with team members about changes
- Pull latest changes before starting work
- Use feature branches to isolate changes

### Rebase Conflicts

**Problem**: Conflicts during interactive rebase.

**Solution**:
```bash
# 1. Resolve conflicts in each commit
# 2. Stage resolved files
git add resolved-file.txt

# 3. Continue rebase
git rebase --continue

# 4. Skip problematic commit
git rebase --skip

# 5. Abort rebase
git rebase --abort
```

### Push Rejected

**Problem**: `git push` fails with "rejected" error.

**Solution**:
```bash
# 1. Pull latest changes first
git pull origin main

# 2. Resolve any conflicts
# 3. Push again
git push origin main

# Alternative: Force push (use with caution!)
git push --force origin main
```

**Prevention**:
- Always pull before pushing
- Use `git pull --rebase` for cleaner history
- Communicate with team about force pushes

### Large File Issues

**Problem**: Repository becomes slow due to large files.

**Solution**:
```bash
# 1. Find large files
git rev-list --objects --all | git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | sed -n 's/^blob //p' | sort -k2nr | head -10

# 2. Remove large files from history
git filter-branch --tree-filter 'rm -f large-file.txt' HEAD

# 3. Clean up
git gc --prune=now
```

**Prevention**:
- Use `.gitignore` for large files
- Use Git LFS for large files
- Monitor repository size regularly

## 🔍 Performance Issues

### Slow Repository

**Symptoms**: Commands take a long time to execute.

**Diagnosis**:
```bash
# Check repository size
du -sh .git

# Count objects
git count-objects -v

# Analyze pack files
git verify-pack -v .git/objects/pack/*.idx | sort -k 3 -n | tail -10
```

**Solutions**:
```bash
# Garbage collection
git gc --aggressive

# Repack objects
git repack -a -d -f --depth=250 --window=250

# Prune unreachable objects
git prune --expire=now

# Optimize for large repositories
git config pack.windowMemory 100m
git config pack.packSizeLimit 100m
git config pack.threads 4
```

### Network Issues

**Problem**: Slow push/pull operations.

**Solutions**:
```bash
# Optimize HTTP settings
git config http.postBuffer 524288000
git config http.maxRequestBuffer 100M
git config http.lowSpeedLimit 0
git config http.lowSpeedTime 999999

# Use shallow clone for large repositories
git clone --depth 1 <url>

# Configure SSH for better performance
# ~/.ssh/config
Host github.com
    Compression yes
    CompressionLevel 9
    TCPKeepAlive yes
    ServerAliveInterval 60
    ServerAliveCountMax 3
```

## 🛠️ Configuration Issues

### Wrong User/Email

**Problem**: Commits show wrong author information.

**Solution**:
```bash
# Check current configuration
git config user.name
git config user.email

# Set correct information
git config user.name "Your Name"
git config user.email "your.email@example.com"

# Set globally
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

### Editor Issues

**Problem**: Git opens wrong editor or editor doesn't work.

**Solution**:
```bash
# Set preferred editor
git config --global core.editor "code --wait"  # VS Code
git config --global core.editor "vim"          # Vim
git config --global core.editor "nano"         # Nano

# Check current editor
git config core.editor
```

### Line Ending Issues

**Problem**: Files show different line endings on different systems.

**Solution**:
```bash
# Configure line ending handling
git config --global core.autocrlf input  # Linux/macOS
git config --global core.autocrlf true   # Windows

# Normalize line endings
git add --renormalize .
git commit -m "Normalize line endings"
```

## 🔐 Authentication Issues

### SSH Key Problems

**Problem**: SSH authentication fails.

**Diagnosis**:
```bash
# Test SSH connection
ssh -T git@github.com

# Check SSH key
ssh-add -l

# Generate new SSH key if needed
ssh-keygen -t ed25519 -C "your.email@example.com"
```

**Solution**:
```bash
# Add SSH key to agent
ssh-add ~/.ssh/id_ed25519

# Add public key to GitHub
cat ~/.ssh/id_ed25519.pub
# Copy to GitHub SSH keys settings
```

### HTTPS Authentication

**Problem**: HTTPS authentication fails.

**Solution**:
```bash
# Use credential manager
git config --global credential.helper manager-core

# Or use credential cache
git config --global credential.helper cache
git config --global credential.helper 'cache --timeout=3600'

# Clear stored credentials
git config --global --unset credential.helper
```

## 📁 File and Directory Issues

### Untracked Files

**Problem**: Files not being tracked by Git.

**Diagnosis**:
```bash
# Check .gitignore
cat .gitignore

# Check if files are ignored
git check-ignore <file>

# Force add ignored file
git add -f <file>
```

**Solution**:
```bash
# Add files to tracking
git add <file>

# Add all files (including ignored)
git add -A

# Check what would be added
git add -n .
```

### Permission Issues

**Problem**: Permission denied errors.

**Solution**:
```bash
# Fix file permissions
chmod 644 <file>
chmod 755 <directory>

# Fix Git hooks permissions
chmod +x .git/hooks/*

# Fix SSH key permissions
chmod 600 ~/.ssh/id_ed25519
chmod 644 ~/.ssh/id_ed25519.pub
```

## 🔄 Workflow Issues

### Wrong Branch

**Problem**: Working on wrong branch.

**Solution**:
```bash
# Stash current changes
git stash

# Switch to correct branch
git checkout correct-branch

# Apply changes
git stash pop

# Or create new branch with changes
git checkout -b new-branch
git stash pop
```

### Committed to Wrong Branch

**Problem**: Made commits on wrong branch.

**Solution**:
```bash
# 1. Create new branch with commits
git checkout -b correct-branch

# 2. Switch back to original branch
git checkout original-branch

# 3. Reset to remove commits
git reset --hard HEAD~n  # n = number of commits to remove

# 4. Switch to correct branch
git checkout correct-branch
```

### Accidentally Committed Large File

**Problem**: Committed large file that should be ignored.

**Solution**:
```bash
# 1. Remove from last commit
git reset --soft HEAD~1

# 2. Remove from staging
git reset HEAD large-file.txt

# 3. Add to .gitignore
echo "large-file.txt" >> .gitignore

# 4. Commit again
git add .
git commit -m "Remove large file and update .gitignore"
```

## 🔍 Debugging Commands

### Repository Information
```bash
# Show repository status
git status

# Show branch information
git branch -vv

# Show remote information
git remote -v

# Show configuration
git config --list
```

### Object Information
```bash
# Show commit details
git show <commit>

# Show file history
git log --follow <file>

# Show blame information
git blame <file>

# Show object type
git cat-file -t <object>
```

### Network Debugging
```bash
# Test remote connection
git ls-remote origin

# Show fetch/push URLs
git remote get-url origin

# Test SSH connection
ssh -T git@github.com

# Debug HTTP requests
GIT_CURL_VERBOSE=1 git fetch
```

## 🛡️ Prevention Strategies

### Regular Maintenance
```bash
# Weekly cleanup
git gc
git prune

# Monthly optimization
git gc --aggressive
git repack -a -d

# Quarterly audit
git count-objects -v
du -sh .git
```

### Backup Strategies
```bash
# Create backup repository
git clone --mirror <original-repo> backup-repo

# Regular backups
git push backup-repo --all
git push backup-repo --tags
```

### Best Practices
- Always pull before pushing
- Use meaningful commit messages
- Keep commits small and focused
- Use branches for features
- Regular backups
- Monitor repository size
- Use appropriate .gitignore files

## 📞 Getting Help

### Git Help
```bash
# Get help for specific command
git help <command>

# Show all commands
git help --all

# Show configuration
git help config
```

### Online Resources
- [Git Documentation](https://git-scm.com/doc)
- [GitHub Help](https://help.github.com/)
- [Stack Overflow](https://stackoverflow.com/questions/tagged/git)
- [Git Community](https://git-scm.com/community)

### Debugging Tools
- [Git GUI Tools](https://git-scm.com/downloads/guis)
- [GitHub Desktop](https://desktop.github.com/)
- [GitKraken](https://www.gitkraken.com/)
- [SourceTree](https://www.sourcetreeapp.com/)

---

**Remember**: Most Git problems can be solved with the right approach. Always start with diagnosis, then apply the appropriate solution. When in doubt, use `git help` or consult the official documentation.