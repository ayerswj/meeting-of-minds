# Module 6: Git Optimization and Troubleshooting

## 🎯 Learning Objectives

By the end of this module, you will be able to:
- Optimize Git performance for large repositories
- Troubleshoot complex Git issues
- Master Git configuration for productivity
- Create custom Git tools and scripts
- Implement advanced recovery techniques
- Build efficient Git workflows

## 📚 Topics Covered

### 1. Performance Optimization

#### Large Repository Optimization

**Repository Analysis:**
```bash
# Check repository size
du -sh .git

# Analyze object count
git count-objects -v

# Find large files in history
git rev-list --objects --all | git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | sed -n 's/^blob //p' | sort -k2nr | head -20

# Analyze pack files
git verify-pack -v .git/objects/pack/*.idx | sort -k 3 -n | tail -10
```

**Performance Configuration:**
```bash
# Enable parallel index preload
git config core.preloadindex true

# Enable filesystem monitor (macOS/Windows)
git config core.fsmonitor true

# Optimize for large repositories
git config pack.windowMemory 100m
git config pack.packSizeLimit 100m
git config pack.threads 4

# Enable delta compression
git config pack.deltaCacheSize 512m
git config pack.deltaCacheLimit 1000

# Optimize network operations
git config http.postBuffer 524288000
git config http.maxRequestBuffer 100M
git config http.lowSpeedLimit 0
git config http.lowSpeedTime 999999
```

**Repository Maintenance:**
```bash
# Aggressive garbage collection
git gc --aggressive --prune=now

# Repack with optimal settings
git repack -a -d --depth=250 --window=250

# Prune unreachable objects
git prune --expire=now

# Optimize pack files
git repack -a -d -f --depth=250 --window=250
```

#### Network Optimization

**SSH Configuration:**
```bash
# ~/.ssh/config
Host github.com
    HostName github.com
    User git
    Compression yes
    CompressionLevel 9
    TCPKeepAlive yes
    ServerAliveInterval 60
    ServerAliveCountMax 3
```

**HTTP/HTTPS Optimization:**
```bash
# Enable HTTP/2
git config http.version HTTP/1.1

# Configure proxy if needed
git config http.proxy http://proxy.example.com:8080

# Enable credential caching
git config credential.helper cache
git config credential.helper 'cache --timeout=3600'

# Use credential manager
git config credential.helper manager-core
```

**Shallow Cloning:**
```bash
# Clone with limited history
git clone --depth 1 https://github.com/user/repo.git

# Fetch additional history
git fetch --depth 100

# Unshallow repository
git fetch --unshallow
```

### 2. Complex Problem Solving

#### Data Recovery

**Recovering Lost Commits:**
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

**Recovering Deleted Branches:**
```bash
# Find deleted branch in reflog
git reflog --all | grep "branch-name"

# Recreate branch from reflog
git checkout -b branch-name <commit-hash>

# Alternative: use git fsck
git fsck --lost-found
```

**Recovering Corrupted Repository:**
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

#### Advanced Conflict Resolution

**Complex Merge Conflicts:**
```bash
# Use merge tool
git mergetool

# Configure merge tool
git config merge.tool vimdiff
git config merge.tool kdiff3
git config merge.tool meld

# Resolve conflicts programmatically
git checkout --ours conflicted-file.txt
git checkout --theirs conflicted-file.txt

# Use custom merge strategy
git merge -X ours feature-branch
git merge -X theirs feature-branch
```

**Rebase Conflicts:**
```bash
# Interactive rebase with conflict resolution
git rebase -i HEAD~5

# Skip problematic commits
git rebase --skip

# Edit commits during rebase
git rebase --edit-todo

# Abort and try different approach
git rebase --abort
```

**Submodule Conflicts:**
```bash
# Update submodules
git submodule update --init --recursive

# Resolve submodule conflicts
cd submodule-directory
git checkout main
git pull origin main
cd ..
git add submodule-directory
git commit -m "Update submodule"
```

### 3. Git Configuration Mastery

#### Advanced Configuration

**Global Configuration:**
```bash
# Editor configuration
git config --global core.editor "code --wait"
git config --global core.editor "vim"
git config --global core.editor "nano"

# Pager configuration
git config --global core.pager "less -FRX"

# Default branch name
git config --global init.defaultBranch main

# Line ending handling
git config --global core.autocrlf input  # Linux/macOS
git config --global core.autocrlf true   # Windows
git config --global core.safecrlf warn

# Credential management
git config --global credential.helper cache
git config --global credential.helper 'cache --timeout=3600'
```

**Repository-Specific Configuration:**
```bash
# Local repository settings
git config user.name "Project Specific Name"
git config user.email "project@example.com"

# Branch-specific settings
git config branch.main.mergeoptions --no-ff
git config branch.develop.mergeoptions --ff-only

# Remote-specific settings
git config remote.origin.fetch "+refs/heads/*:refs/remotes/origin/*"
git config remote.upstream.fetch "+refs/heads/*:refs/remotes/upstream/*"
```

**Alias Configuration:**
```bash
# Common aliases
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit
git config --global alias.st status
git config --global alias.unstage 'reset HEAD --'
git config --global alias.last 'log -1 HEAD'
git config --global alias.visual '!gitk'

# Advanced aliases
git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"
git config --global alias.stats "log --stat --oneline"
git config --global alias.changes "diff --name-status"
git config --global alias.undo "reset --soft HEAD~1"
```

#### Environment Optimization

**Shell Integration:**
```bash
# Bash completion
source /usr/share/bash-completion/completions/git

# Zsh completion
autoload -Uz compinit && compinit

# Fish completion
# Install via: fisher install IlanCosman/tide@v5
```

**Prompt Configuration:**
```bash
# Git status in prompt (bash)
export PS1='\w$(__git_ps1 " (%s)")\$ '

# Git status in prompt (zsh)
autoload -Uz vcs_info
precmd() { vcs_info }
zstyle ':vcs_info:git:*' formats ' (%b)'
setopt PROMPT_SUBST
PROMPT='%n@%m %F{green}%~%F{blue}${vcs_info_msg_0_}%F{white} $ '
```

### 4. Custom Git Tools and Scripts

#### Git Hooks for Automation

**Pre-commit Hook:**
```bash
#!/bin/sh
# .git/hooks/pre-commit

# Run tests
if [ -f "package.json" ]; then
    npm test
fi

# Check code style
if [ -f "package.json" ]; then
    npm run lint
fi

# Check for TODO comments
if git diff --cached | grep -i "TODO"; then
    echo "Error: TODO comments found in staged files"
    exit 1
fi

# Check commit message format
commit_msg=$(cat "$1")
if ! echo "$commit_msg" | grep -qE "^(feat|fix|docs|style|refactor|test|chore)(\(.+\))?: .+"; then
    echo "Error: Commit message must follow conventional format"
    exit 1
fi
```

**Post-commit Hook:**
```bash
#!/bin/sh
# .git/hooks/post-commit

# Send notification
commit_hash=$(git rev-parse HEAD)
commit_msg=$(git log -1 --pretty=format:%s)
author=$(git log -1 --pretty=format:%an)

echo "Commit $commit_hash by $author: $commit_msg"

# Update deployment status
curl -X POST https://api.example.com/deploy/status \
  -H "Content-Type: application/json" \
  -d "{\"commit\":\"$commit_hash\",\"status\":\"pending\"}"
```

**Pre-push Hook:**
```bash
#!/bin/sh
# .git/hooks/pre-push

# Run full test suite
npm run test:full

# Check for sensitive data
if git diff --cached | grep -i "password\|secret\|key"; then
    echo "Warning: Potential sensitive data detected"
    read -p "Continue anyway? (y/N) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi
```

#### Custom Git Commands

**Git Scripts:**
```bash
#!/bin/bash
# git-feature

# Create feature branch with proper naming
feature_name=$1
if [ -z "$feature_name" ]; then
    echo "Usage: git feature <feature-name>"
    exit 1
fi

# Ensure we're on main and up to date
git checkout main
git pull origin main

# Create feature branch
git checkout -b "feature/$feature_name"

echo "Created feature branch: feature/$feature_name"
```

```bash
#!/bin/bash
# git-cleanup

# Clean up merged branches
echo "Cleaning up merged branches..."

# Delete local merged branches
git branch --merged | grep -v "\*" | grep -v "main" | grep -v "develop" | xargs -n 1 git branch -d

# Delete remote tracking branches for deleted remote branches
git remote prune origin

# Clean up stashes older than 30 days
git reflog expire --expire=30.days.ago refs/stash

echo "Cleanup complete!"
```

```bash
#!/bin/bash
# git-sync

# Sync with remote repository
echo "Syncing with remote..."

# Fetch all changes
git fetch --all

# Update current branch
current_branch=$(git branch --show-current)
git pull origin "$current_branch"

# Clean up
git remote prune origin
git gc --prune=now

echo "Sync complete!"
```

#### Git Workflow Scripts

**Release Script:**
```bash
#!/bin/bash
# git-release

version=$1
if [ -z "$version" ]; then
    echo "Usage: git release <version>"
    exit 1
fi

# Ensure we're on main
git checkout main
git pull origin main

# Create release branch
git checkout -b "release/v$version"

# Update version in files
# (Add your version update logic here)

# Commit version update
git add .
git commit -m "chore: prepare release v$version"

# Create tag
git tag -a "v$version" -m "Release v$version"

# Push release branch and tag
git push origin "release/v$version"
git push origin "v$version"

echo "Release v$version prepared!"
```

### 5. Advanced Recovery Techniques

#### Repository Recovery

**Complete Repository Recovery:**
```bash
#!/bin/bash
# git-recover

# Recover from backup or recreate repository
if [ -d ".git" ]; then
    echo "Backing up current .git directory..."
    mv .git .git.backup.$(date +%Y%m%d_%H%M%S)
fi

# Reinitialize repository
git init

# Add remote
git remote add origin <repository-url>

# Fetch all branches
git fetch origin

# Checkout main branch
git checkout main

echo "Repository recovered!"
```

**Data Recovery from Corrupted Repository:**
```bash
#!/bin/bash
# git-repair

echo "Attempting to repair corrupted repository..."

# Check repository integrity
git fsck --full

# Recover lost objects
git fsck --lost-found

# Rebuild index
rm -f .git/index
git reset

# Rebuild refs
git for-each-ref --format='%(refname)' refs/heads | while read ref; do
    if ! git rev-parse --verify "$ref" >/dev/null 2>&1; then
        echo "Removing invalid ref: $ref"
        git update-ref -d "$ref"
    fi
done

echo "Repair attempt complete!"
```

#### Advanced Git Operations

**Bulk Operations:**
```bash
#!/bin/bash
# git-bulk-commit

# Commit all changes in subdirectories
for dir in */; do
    if [ -d "$dir/.git" ]; then
        echo "Processing $dir..."
        cd "$dir"
        if [ -n "$(git status --porcelain)" ]; then
            git add .
            git commit -m "Auto-commit $(date)"
            git push
        fi
        cd ..
    fi
done
```

**Repository Migration:**
```bash
#!/bin/bash
# git-migrate

old_url=$1
new_url=$2

if [ -z "$old_url" ] || [ -z "$new_url" ]; then
    echo "Usage: git migrate <old-url> <new-url>"
    exit 1
fi

# Change remote URL
git remote set-url origin "$new_url"

# Push all branches
git push --all origin

# Push all tags
git push --tags origin

echo "Migration complete!"
```

## 🛠️ Hands-On Exercises

### Exercise 1: Performance Optimization
1. Analyze a large repository
2. Identify performance bottlenecks
3. Implement optimization strategies
4. Measure performance improvements

### Exercise 2: Complex Troubleshooting
1. Create and resolve complex merge conflicts
2. Recover lost data using various techniques
3. Repair corrupted repositories
4. Handle submodule issues

### Exercise 3: Custom Tools Development
1. Create custom Git scripts
2. Implement automated workflows
3. Build productivity tools
4. Test and refine custom solutions

### Exercise 4: Advanced Configuration
1. Optimize Git configuration for your environment
2. Set up automated workflows
3. Configure team-wide standards
4. Implement security best practices

## 📝 Practice Project: Enterprise Git Infrastructure

Create a complete Git infrastructure for enterprise use:

1. **Repository Management**
   - Set up multiple repositories
   - Configure access controls
   - Implement backup strategies
   - Monitor repository health

2. **Automation and CI/CD**
   - Automated testing and deployment
   - Code quality checks
   - Security scanning
   - Performance monitoring

3. **Team Workflows**
   - Standardized processes
   - Code review automation
   - Release management
   - Documentation generation

4. **Monitoring and Maintenance**
   - Repository performance monitoring
   - Automated cleanup scripts
   - Health checks and alerts
   - Disaster recovery procedures

## ❓ Assessment Questions

1. How do you optimize Git performance for large repositories?
2. What are the steps to recover from a corrupted repository?
3. How do you create custom Git commands and scripts?
4. What are the best practices for Git configuration management?
5. How do you handle complex merge conflicts in large teams?
6. What are the key considerations for enterprise Git infrastructure?
7. How do you implement automated Git workflows?

## 🔗 Additional Resources

- [Git Performance](https://git-scm.com/book/en/v2/Git-Internals-Maintenance-and-Data-Recovery)
- [Git Hooks](https://git-scm.com/docs/githooks)
- [Git Configuration](https://git-scm.com/docs/git-config)
- [Git Recovery](https://git-scm.com/book/en/v2/Git-Tools-Debugging-with-Git)

## 🎯 Course Completion

Congratulations! You have completed the comprehensive Git Mastery course. You are now equipped with:

- **Fundamental Knowledge**: Understanding of Git's core concepts and philosophy
- **Practical Skills**: Mastery of all essential Git commands and workflows
- **Advanced Techniques**: Ability to handle complex scenarios and optimize performance
- **Collaboration Expertise**: Skills for effective team collaboration and code review
- **Troubleshooting Mastery**: Ability to solve complex Git problems and recover from issues
- **Customization Skills**: Knowledge to create custom tools and optimize workflows

## 🚀 Next Steps

- **Apply Your Knowledge**: Use these skills in real projects
- **Share Your Expertise**: Help others learn Git
- **Stay Updated**: Follow Git development and new features
- **Contribute**: Participate in open source projects
- **Mentor**: Guide others in their Git journey

---

**Final Thought**: Git mastery is not just about knowing commands—it's about understanding the philosophy of version control and using it to create better software collaboratively. Keep practicing, keep learning, and keep improving your workflows.