# Module 5: Git Internals and Advanced Topics

## 🎯 Learning Objectives

By the end of this module, you will be able to:
- Understand Git's internal object model
- Work with Git references and refspecs
- Implement advanced merging strategies
- Use Git submodules and subtrees effectively
- Understand Git's data structures
- Master advanced Git operations

## 📚 Topics Covered

### 1. Git Object Model

#### Understanding Git Objects

Git stores everything as objects in the `.git/objects` directory. There are four types of objects:

**1. Blob Objects:**
```bash
# View blob content
git show <blob-hash>

# Find blob hash for a file
git hash-object filename.txt

# Create blob manually
echo "content" | git hash-object -w --stdin
```

**2. Tree Objects:**
```bash
# View tree structure
git ls-tree <tree-hash>

# Show tree recursively
git ls-tree -r <tree-hash>

# Create tree from index
git write-tree
```

**3. Commit Objects:**
```bash
# View commit details
git cat-file commit <commit-hash>

# Show commit tree
git show <commit-hash> --name-only

# Create commit manually
git commit-tree <tree-hash> -m "message"
```

**4. Tag Objects:**
```bash
# View tag object
git cat-file tag <tag-hash>

# Create annotated tag object
git mktag
```

#### Object Storage and Compression

**Object Storage:**
```bash
# Objects are stored as compressed files
# Path: .git/objects/ab/c1234...
# Where ab is first two chars of hash, c1234... is the rest

# View object size
git cat-file -s <object-hash>

# View object type
git cat-file -t <object-hash>

# View object content
git cat-file -p <object-hash>
```

**Object Compression:**
```bash
# Git uses zlib compression
# Objects are delta-compressed for efficiency

# Repack objects
git gc

# Aggressive repacking
git gc --aggressive

# Prune unreachable objects
git prune
```

### 2. Git References and Refspecs

#### Understanding References

**Reference Types:**
- **HEAD**: Points to current branch or commit
- **Branch refs**: `refs/heads/branch-name`
- **Remote refs**: `refs/remotes/remote/branch`
- **Tag refs**: `refs/tags/tag-name`

**Working with References:**
```bash
# Show current HEAD
git show HEAD

# Show branch reference
git show refs/heads/main

# Show remote reference
git show refs/remotes/origin/main

# Show tag reference
git show refs/tags/v1.0.0
```

#### Refspecs

**Refspec Format:**
```
<source>:<destination>
```

**Common Refspecs:**
```bash
# Push local main to remote main
git push origin main:main

# Push local feature to remote feature
git push origin feature:feature

# Fetch remote develop to local develop
git fetch origin develop:develop

# Delete remote branch
git push origin :feature-branch
```

**Advanced Refspecs:**
```bash
# Push all local branches
git push origin refs/heads/*:refs/heads/*

# Fetch all remote branches
git fetch origin refs/heads/*:refs/remotes/origin/*

# Push tags
git push origin refs/tags/*:refs/tags/*
```

#### Reference Logs

**Viewing Reflog:**
```bash
# Show reflog for HEAD
git reflog

# Show reflog for specific branch
git reflog show main

# Show reflog with dates
git reflog --date=iso

# Show reflog for specific time
git reflog --since="2 days ago"
```

**Using Reflog for Recovery:**
```bash
# Recover lost commit
git checkout <commit-hash>

# Create branch from reflog entry
git checkout -b recovery-branch HEAD@{1}

# Reset to previous state
git reset --hard HEAD@{1}
```

### 3. Advanced Merging Strategies

#### Merge Strategies

**Recursive Merge (Default):**
```bash
# Three-way merge with common ancestor
git merge feature-branch

# With strategy options
git merge -s recursive -X ours feature-branch
git merge -s recursive -X theirs feature-branch
```

**Octopus Merge:**
```bash
# Merge multiple branches at once
git merge branch1 branch2 branch3
```

**Subtree Merge:**
```bash
# Merge external project into subdirectory
git merge -s subtree --prefix=external/ external-branch
```

**Resolve Strategy:**
```bash
# Use resolve strategy (simpler than recursive)
git merge -s resolve feature-branch
```

#### Advanced Conflict Resolution

**Conflict Resolution Strategies:**
```bash
# Use ours strategy for all conflicts
git merge -X ours feature-branch

# Use theirs strategy for all conflicts
git merge -X theirs feature-branch

# Ignore whitespace changes
git merge -X ignore-space-change feature-branch

# Ignore all whitespace
git merge -X ignore-all-space feature-branch
```

**Manual Conflict Resolution:**
```bash
# Abort merge
git merge --abort

# Continue merge after resolving
git merge --continue

# Skip conflicted files
git reset HEAD conflicted-file.txt
git checkout -- conflicted-file.txt
```

#### Rebase Strategies

**Interactive Rebase:**
```bash
# Rebase with interactive editing
git rebase -i HEAD~5

# Available commands:
# pick - use commit
# reword - use commit, but edit message
# edit - use commit, but stop for amending
# squash - use commit, but meld into previous
# fixup - like squash, but discard message
# exec - run command using shell
# drop - remove commit
```

**Rebase with Conflicts:**
```bash
# Resolve conflicts during rebase
git rebase --continue

# Skip current commit
git rebase --skip

# Abort rebase
git rebase --abort
```

### 4. Git Submodules and Subtrees

#### Git Submodules

**Adding Submodules:**
```bash
# Add submodule
git submodule add https://github.com/user/library.git lib/library

# Initialize submodules
git submodule init

# Update submodules
git submodule update

# Clone with submodules
git clone --recursive https://github.com/user/project.git
```

**Working with Submodules:**
```bash
# Update submodule to latest
git submodule update --remote

# Update specific submodule
git submodule update --remote lib/library

# Enter submodule directory
cd lib/library
git checkout main
git pull origin main
cd ../..

# Commit submodule changes
git add lib/library
git commit -m "Update submodule to latest version"
```

**Submodule Management:**
```bash
# Remove submodule
git submodule deinit lib/library
git rm lib/library
git commit -m "Remove submodule"

# List submodules
git submodule status

# Show submodule summary
git submodule summary
```

#### Git Subtrees

**Adding Subtrees:**
```bash
# Add subtree
git subtree add --prefix=lib/library https://github.com/user/library.git main --squash

# Add subtree with specific commit
git subtree add --prefix=lib/library https://github.com/user/library.git main --squash
```

**Managing Subtrees:**
```bash
# Pull changes from subtree
git subtree pull --prefix=lib/library https://github.com/user/library.git main --squash

# Push changes to subtree
git subtree push --prefix=lib/library https://github.com/user/library.git main

# Split subtree into separate repository
git subtree split --prefix=lib/library --onto=https://github.com/user/library.git main
```

**Subtree vs Submodule Comparison:**

| Feature | Submodule | Subtree |
|---------|-----------|---------|
| Repository size | Small (reference only) | Large (includes code) |
| Complexity | High | Low |
| Network access | Required for updates | Not required |
| History | Separate | Integrated |
| Cloning | Requires --recursive | Standard clone |

### 5. Advanced Git Operations

#### Git Plumbing Commands

**Low-level Commands:**
```bash
# Hash object
git hash-object -w filename.txt

# Create tree from index
git write-tree

# Create commit
git commit-tree <tree-hash> -p <parent-hash> -m "message"

# Update reference
git update-ref refs/heads/branch-name <commit-hash>
```

**Object Inspection:**
```bash
# Find object type
git cat-file -t <object-hash>

# Show object size
git cat-file -s <object-hash>

# Show object content
git cat-file -p <object-hash>

# Show object in binary
git cat-file -w <object-hash>
```

#### Advanced Log and History

**Complex Log Queries:**
```bash
# Show commits affecting specific file
git log --follow filename.txt

# Show commits by author
git log --author="John Doe"

# Show commits by date range
git log --since="2023-01-01" --until="2023-12-31"

# Show commits with specific text
git log -S "function name"

# Show merge commits only
git log --merges

# Show non-merge commits only
git log --no-merges
```

**Graph Visualization:**
```bash
# Show commit graph
git log --graph --oneline --all

# Show graph with decorations
git log --graph --oneline --all --decorate

# Show graph with colors
git log --graph --oneline --all --color
```

#### Advanced Branching

**Branch Tracking:**
```bash
# Set up tracking
git branch --set-upstream-to=origin/main main

# Create branch with tracking
git checkout -b feature origin/feature

# Show tracking information
git branch -vv
```

**Branch Management:**
```bash
# Rename branch
git branch -m old-name new-name

# Delete merged branches
git branch --merged | grep -v "\*" | xargs -n 1 git branch -d

# Delete remote tracking branches
git remote prune origin
```

### 6. Git Performance and Optimization

#### Repository Optimization

**Garbage Collection:**
```bash
# Run garbage collection
git gc

# Aggressive garbage collection
git gc --aggressive

# Prune unreachable objects
git prune

# Full repository optimization
git gc --prune=now
```

**Repository Size Management:**
```bash
# Check repository size
du -sh .git

# Find large files
git rev-list --objects --all | git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | sed -n 's/^blob //p' | sort -k2nr | head -10

# Remove large files from history
git filter-branch --tree-filter 'rm -f large-file.txt' HEAD
```

**Performance Configuration:**
```bash
# Enable parallel index preload
git config core.preloadindex true

# Enable filesystem monitor
git config core.fsmonitor true

# Optimize for large repositories
git config pack.windowMemory 100m
git config pack.packSizeLimit 100m
```

## 🛠️ Hands-On Exercises

### Exercise 1: Git Object Exploration
1. Create a simple repository
2. Explore blob, tree, and commit objects
3. Manually create objects using plumbing commands
4. Understand object relationships

### Exercise 2: Advanced Merging
1. Create complex merge scenarios
2. Use different merge strategies
3. Resolve conflicts using various approaches
4. Practice rebase workflows

### Exercise 3: Submodules and Subtrees
1. Add external projects as submodules
2. Convert submodules to subtrees
3. Manage dependencies effectively
4. Handle updates and conflicts

### Exercise 4: Performance Optimization
1. Analyze repository size
2. Identify performance bottlenecks
3. Optimize repository configuration
4. Clean up unnecessary objects

## 📝 Practice Project: Complex Repository Management

Create a complex project with multiple dependencies:

1. **Repository Structure**
   - Main application
   - Multiple submodules for libraries
   - Subtrees for shared components
   - Complex branching strategy

2. **Advanced Workflows**
   - Custom merge strategies
   - Automated conflict resolution
   - Performance optimization
   - Repository maintenance

3. **Integration Testing**
   - Test submodule updates
   - Verify subtree operations
   - Validate merge strategies
   - Performance benchmarking

## ❓ Assessment Questions

1. What are the four types of Git objects and their purposes?
2. How do you manually create and manipulate Git objects?
3. What is the difference between submodules and subtrees?
4. How do you use reflog for recovery operations?
5. What are the different merge strategies available in Git?
6. How do you optimize Git repository performance?
7. What are refspecs and how do you use them?

## 🔗 Additional Resources

- [Git Internals](https://git-scm.com/book/en/v2/Git-Internals-Plumbing-and-Porcelain)
- [Git Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)
- [Git Performance](https://git-scm.com/book/en/v2/Git-Internals-Maintenance-and-Data-Recovery)
- [Advanced Git](https://git-scm.com/book/en/v2/Git-Tools-Advanced-Merging)

## 🎯 Next Steps

Once you've completed this module:
- Master Git's internal workings
- Optimize repository performance
- Handle complex merge scenarios
- Move on to **Module 6: Git Optimization and Troubleshooting**

---

**Expert Insight**: Understanding Git's internals gives you the power to solve complex problems and optimize your workflow. This knowledge is essential for advanced Git usage.