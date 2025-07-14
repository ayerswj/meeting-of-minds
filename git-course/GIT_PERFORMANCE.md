# Git Performance Optimization Guide

A comprehensive guide to optimizing Git performance for large repositories, improving workflow efficiency, and troubleshooting performance issues.

## 🚀 Performance Fundamentals

### Understanding Git Performance
Git performance is affected by several factors:
- **Repository Size**: Number of files, commits, and branches
- **Network**: Connection speed and latency
- **Storage**: Disk I/O and file system performance
- **Memory**: Available RAM for Git operations
- **CPU**: Processing power for complex operations

### Performance Metrics
```bash
# Measure repository size
du -sh .git
git count-objects -v

# Measure clone time
time git clone https://github.com/user/repo.git

# Measure push/pull time
time git push origin main
time git pull origin main

# Measure log performance
time git log --oneline --all
```

## 📊 Repository Analysis

### Repository Size Analysis
```bash
# Analyze repository size
git count-objects -v

# Find large files
git rev-list --objects --all | \
  git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | \
  sed -n 's/^blob //p' | \
  sort -k2nr | \
  head -20

# Analyze pack files
git verify-pack -v .git/objects/pack/*.idx | \
  sort -k 3 -n | \
  tail -10

# Show repository statistics
git shortlog -s -n --all
git branch -a | wc -l
git tag | wc -l
```

### Performance Bottlenecks
```bash
# Check for performance issues
git gc --prune=now
git count-objects -v

# Analyze object types
git rev-list --objects --all | \
  git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | \
  awk '{print $1}' | sort | uniq -c

# Check for loose objects
find .git/objects -type f | grep -v pack | wc -l
```

## 🔧 Repository Optimization

### Garbage Collection
```bash
# Basic garbage collection
git gc

# Aggressive garbage collection
git gc --aggressive

# Prune unreachable objects
git gc --prune=now

# Prune objects older than specific date
git gc --prune=1.week.ago

# Full optimization
git gc --aggressive --prune=now
```

### Pack File Optimization
```bash
# Repack with default settings
git repack -a -d

# Repack with aggressive settings
git repack -a -d -f --depth=250 --window=250

# Repack with memory optimization
git repack -a -d -f --depth=250 --window=250 --window-memory=100m

# Repack with thread optimization
git repack -a -d -f --depth=250 --window=250 --threads=4

# Verify pack files
git verify-pack -v .git/objects/pack/*.idx
```

### Delta Compression Optimization
```bash
# Optimize delta chains
git repack -a -d -f --depth=250 --window=250

# Show delta statistics
git verify-pack -v .git/objects/pack/*.idx | \
  grep -E "(delta|chain)" | \
  awk '{print $1, $2, $3}' | \
  sort -k3 -n

# Analyze delta efficiency
git verify-pack -v .git/objects/pack/*.idx | \
  grep -E "delta" | \
  awk '{total += $3; count++} END {print "Average delta size:", total/count}'
```

## 🌐 Network Optimization

### Clone Optimization
```bash
# Shallow clone (recent history only)
git clone --depth 1 https://github.com/user/repo.git

# Clone specific branch only
git clone --single-branch --branch main https://github.com/user/repo.git

# Clone with limited history
git clone --depth 100 https://github.com/user/repo.git

# Clone with compression
git clone --depth 1 --single-branch https://github.com/user/repo.git
```

### Fetch and Pull Optimization
```bash
# Fetch with depth limit
git fetch --depth 1 origin main

# Fetch specific branch only
git fetch origin main:main

# Pull with rebase (faster than merge)
git pull --rebase origin main

# Fetch with compression
git fetch --depth 1 --single-branch origin main
```

### Push Optimization
```bash
# Push with compression
git push --compress=9 origin main

# Push specific branch only
git push origin main:main

# Push with progress
git push --progress origin main
```

### SSH Optimization
```bash
# ~/.ssh/config optimization
Host github.com
    HostName github.com
    User git
    Compression yes
    CompressionLevel 9
    TCPKeepAlive yes
    ServerAliveInterval 60
    ServerAliveCountMax 3
    ControlMaster auto
    ControlPath ~/.ssh/control-%h-%p-%r
    ControlPersist 1h
```

### HTTP Optimization
```bash
# Configure HTTP settings
git config --global http.postBuffer 524288000
git config --global http.maxRequestBuffer 100M
git config --global http.lowSpeedLimit 0
git config --global http.lowSpeedTime 999999
git config --global http.version HTTP/1.1
git config --global http.extraHeader "A-IM: digest"
```

## 💾 Storage Optimization

### File System Optimization
```bash
# Use fast file system
# ext4, xfs, or zfs for better performance

# Optimize file system settings
# For ext4: tune2fs -O has_journal /dev/sdX
# For xfs: xfs_info /dev/sdX

# Use SSD storage when possible
# Monitor disk I/O: iostat -x 1
```

### Git Configuration for Storage
```bash
# Optimize for large repositories
git config pack.windowMemory 100m
git config pack.packSizeLimit 100m
git config pack.threads 4
git config pack.deltaCacheSize 512m
git config pack.deltaCacheLimit 2048

# Optimize index
git config core.preloadindex true
git config core.fscache true
git config gc.auto 256

# Optimize for your file system
git config core.autocrlf input  # Linux/macOS
git config core.autocrlf true   # Windows
```

## 🧠 Memory Optimization

### Git Memory Settings
```bash
# Configure memory limits
git config pack.windowMemory 100m
git config pack.packSizeLimit 100m
git config pack.deltaCacheSize 512m
git config pack.deltaCacheLimit 2048

# Optimize for available RAM
# For 8GB RAM:
git config pack.windowMemory 200m
git config pack.packSizeLimit 200m

# For 16GB+ RAM:
git config pack.windowMemory 500m
git config pack.packSizeLimit 500m
```

### Process Memory Management
```bash
# Monitor Git memory usage
ps aux | grep git
top -p $(pgrep git)

# Limit memory usage for large operations
ulimit -v 2097152  # 2GB virtual memory limit
git gc --aggressive

# Use background processes for heavy operations
nohup git gc --aggressive > gc.log 2>&1 &
```

## ⚡ Workflow Optimization

### Log Performance
```bash
# Optimize log commands
git log --oneline --graph --decorate --all
git log --pretty=format:"%h %s" --graph --all

# Use shallow history when possible
git log --oneline -10
git log --oneline --since="1 month ago"

# Use specific paths
git log --oneline -- path/to/file
git log --oneline --follow path/to/file
```

### Status Performance
```bash
# Fast status check
git status --porcelain

# Check specific files
git status --porcelain path/to/file

# Use status with ignore
git status --ignored
```

### Diff Performance
```bash
# Optimize diff commands
git diff --name-only
git diff --stat
git diff --numstat

# Use specific paths
git diff -- path/to/file
git diff HEAD~1 -- path/to/file

# Use cached diff
git diff --cached --name-only
```

### Branch Operations
```bash
# Fast branch switching
git checkout -q branch-name

# List branches efficiently
git branch --list
git branch -r --list

# Delete merged branches
git branch --merged | grep -v "\*" | xargs -n 1 git branch -d
```

## 🔍 Performance Monitoring

### Performance Profiling
```bash
# Profile Git commands
time git log --oneline --all
time git status
time git diff --cached

# Use strace for system calls
strace -c git log --oneline --all

# Use perf for detailed profiling
perf record git log --oneline --all
perf report
```

### Performance Metrics
```bash
# Repository metrics
echo "Repository size: $(du -sh .git | cut -f1)"
echo "Object count: $(git count-objects | grep objects | cut -d' ' -f3)"
echo "Pack count: $(ls .git/objects/pack/*.pack 2>/dev/null | wc -l)"
echo "Branch count: $(git branch -a | wc -l)"
echo "Tag count: $(git tag | wc -l)"

# Performance benchmarks
echo "Log performance:"
time git log --oneline --all > /dev/null

echo "Status performance:"
time git status > /dev/null

echo "Diff performance:"
time git diff --cached > /dev/null
```

### Continuous Monitoring
```bash
#!/bin/bash
# scripts/git-performance-monitor.sh

echo "Git Performance Monitor - $(date)"

# Repository size
repo_size=$(du -sh .git | cut -f1)
echo "Repository size: $repo_size"

# Object count
object_count=$(git count-objects | grep objects | cut -d' ' -f3)
echo "Object count: $object_count"

# Loose objects
loose_objects=$(find .git/objects -type f | grep -v pack | wc -l)
echo "Loose objects: $loose_objects"

# Performance tests
echo "Performance tests:"
time git log --oneline -10 > /dev/null 2>&1
time git status > /dev/null 2>&1

echo "---"
```

## 🛠️ Large Repository Optimization

### Monorepo Optimization
```bash
# Use sparse checkout
git sparse-checkout init --cone
git sparse-checkout set path/to/subdirectory

# Use partial clone
git clone --filter=blob:none https://github.com/user/repo.git
git clone --filter=tree:0 https://github.com/user/repo.git

# Use shallow clone for specific paths
git clone --depth 1 --filter=blob:none --sparse https://github.com/user/repo.git
cd repo
git sparse-checkout init --cone
git sparse-checkout set path/to/needed/files
```

### Submodule Optimization
```bash
# Clone with submodules
git clone --recurse-submodules https://github.com/user/repo.git

# Update submodules efficiently
git submodule update --init --recursive

# Parallel submodule operations
git submodule update --init --recursive --jobs 8
```

### LFS (Large File Storage) Optimization
```bash
# Install Git LFS
git lfs install

# Track large files
git lfs track "*.psd"
git lfs track "*.zip"
git lfs track "*.pdf"

# Optimize LFS
git lfs migrate import --include="*.psd,*.zip,*.pdf"

# Check LFS status
git lfs status
git lfs ls-files
```

## 🔧 Advanced Optimization Techniques

### Custom Git Aliases for Performance
```bash
# Fast status
git config --global alias.st 'status --porcelain'

# Fast log
git config --global alias.lg 'log --oneline --graph --decorate'

# Fast diff
git config --global alias.df 'diff --name-only'

# Performance check
git config --global alias.perf '!echo "Repo size: $(du -sh .git | cut -f1)" && echo "Objects: $(git count-objects | grep objects | cut -d" " -f3)"'
```

### Script Optimization
```bash
#!/bin/bash
# scripts/optimize-repo.sh

echo "Optimizing Git repository..."

# Clean up
git gc --aggressive --prune=now

# Repack with optimal settings
git repack -a -d -f --depth=250 --window=250 --window-memory=100m --threads=4

# Remove old reflog entries
git reflog expire --expire=now --all

# Clean up refs
git for-each-ref --format="%(refname)" refs/original/ | xargs -n 1 git update-ref -d 2>/dev/null || true

echo "Optimization complete!"
```

### Automated Maintenance
```bash
#!/bin/bash
# scripts/git-maintenance.sh

# Run weekly maintenance
if [ "$(date +%u)" -eq 1 ]; then
    echo "Running weekly Git maintenance..."
    
    # Garbage collection
    git gc --aggressive --prune=now
    
    # Repack
    git repack -a -d -f --depth=250 --window=250
    
    # Clean reflog
    git reflog expire --expire=1.week.ago --all
    
    echo "Weekly maintenance complete!"
fi
```

## 📊 Performance Benchmarks

### Benchmark Script
```bash
#!/bin/bash
# scripts/benchmark-git.sh

echo "Git Performance Benchmark"
echo "========================"

# Repository info
echo "Repository Information:"
echo "Size: $(du -sh .git | cut -f1)"
echo "Objects: $(git count-objects | grep objects | cut -d' ' -f3)"
echo "Branches: $(git branch -a | wc -l)"
echo ""

# Benchmark tests
echo "Performance Tests:"
echo "1. Git status:"
time git status > /dev/null 2>&1

echo "2. Git log (last 10):"
time git log --oneline -10 > /dev/null 2>&1

echo "3. Git log (all):"
time git log --oneline --all > /dev/null 2>&1

echo "4. Git diff (cached):"
time git diff --cached > /dev/null 2>&1

echo "5. Git branch list:"
time git branch -a > /dev/null 2>&1

echo "Benchmark complete!"
```

## 🚨 Performance Troubleshooting

### Common Performance Issues

#### Slow Clone
```bash
# Check network
ping github.com
curl -I https://github.com

# Use shallow clone
git clone --depth 1 https://github.com/user/repo.git

# Use specific branch
git clone --single-branch --branch main https://github.com/user/repo.git
```

#### Slow Push/Pull
```bash
# Check network settings
git config --global http.postBuffer 524288000
git config --global http.maxRequestBuffer 100M

# Use compression
git config --global core.compression 9

# Check remote URL
git remote -v
```

#### Slow Log
```bash
# Use shallow history
git log --oneline -10

# Use specific paths
git log --oneline -- path/to/file

# Use date limits
git log --oneline --since="1 month ago"
```

#### High Memory Usage
```bash
# Reduce memory settings
git config pack.windowMemory 50m
git config pack.packSizeLimit 50m

# Use background processes
nohup git gc --aggressive > gc.log 2>&1 &
```

## 📚 Performance Resources

- [Git Performance Documentation](https://git-scm.com/book/en/v2/Git-Internals-Maintenance-and-Data-Recovery)
- [Git Large File Storage](https://git-lfs.github.com/)
- [Git Sparse Checkout](https://git-scm.com/docs/git-sparse-checkout)
- [Git Partial Clone](https://git-scm.com/docs/partial-clone)

---

**Remember**: Performance optimization is an iterative process. Monitor your repository's performance regularly and adjust settings based on your specific use case and hardware capabilities.