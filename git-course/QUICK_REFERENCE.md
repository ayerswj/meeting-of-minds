# Git Quick Reference Guide

A comprehensive reference for all Git commands, concepts, and workflows covered in the Git Mastery course.

## 🚀 Essential Commands

### Repository Setup
```bash
# Initialize new repository
git init

# Clone existing repository
git clone <url>
git clone <url> <directory>

# Add remote repository
git remote add <name> <url>

# List remotes
git remote -v
```

### Basic Workflow
```bash
# Check status
git status

# Add files to staging
git add <file>
git add .
git add *.txt

# Commit changes
git commit -m "message"
git commit -am "message"  # Add and commit tracked files

# View history
git log
git log --oneline
git log --graph --oneline --all
```

### Branching
```bash
# List branches
git branch
git branch -a

# Create branch
git branch <name>
git checkout -b <name>
git switch -c <name>

# Switch branches
git checkout <name>
git switch <name>

# Delete branch
git branch -d <name>
git branch -D <name>  # Force delete
```

### Merging and Rebasing
```bash
# Merge branch
git merge <branch>

# Rebase branch
git rebase <branch>
git rebase -i HEAD~n

# Abort operations
git merge --abort
git rebase --abort
```

### Remote Operations
```bash
# Fetch changes
git fetch <remote>
git fetch --all

# Pull changes
git pull <remote> <branch>
git pull --rebase <remote> <branch>

# Push changes
git push <remote> <branch>
git push -u <remote> <branch>  # Set upstream
git push --force <remote> <branch>  # Force push
```

## 🔧 Advanced Commands

### Stashing
```bash
# Stash changes
git stash
git stash push -m "message"
git stash push <file>

# List stashes
git stash list

# Apply stash
git stash pop
git stash apply stash@{n}

# Drop stash
git stash drop stash@{n}
git stash clear
```

### Cherry-picking
```bash
# Cherry-pick commit
git cherry-pick <commit>

# Cherry-pick multiple commits
git cherry-pick <commit1> <commit2>

# Cherry-pick range
git cherry-pick <start>..<end>

# Continue/abort
git cherry-pick --continue
git cherry-pick --abort
```

### Tags
```bash
# Create tag
git tag <name>
git tag -a <name> -m "message"
git tag -s <name> -m "message"  # Signed tag

# List tags
git tag
git tag -l "v1.*"

# Delete tag
git tag -d <name>
git push origin --delete <name>

# Push tags
git push origin <name>
git push origin --tags
```

### Reset and Revert
```bash
# Reset to commit
git reset --soft <commit>   # Keep changes staged
git reset --mixed <commit>  # Keep changes unstaged
git reset --hard <commit>   # Discard changes

# Revert commit
git revert <commit>
git revert <start>..<end>
```

## 🏗️ Git Flow Commands

```bash
# Initialize Git Flow
git flow init

# Feature branches
git flow feature start <name>
git flow feature finish <name>

# Release branches
git flow release start <version>
git flow release finish <version>

# Hotfix branches
git flow hotfix start <name>
git flow hotfix finish <name>
```

## 🔍 Inspection Commands

### Log and History
```bash
# Basic log
git log
git log --oneline
git log --graph

# Filtered log
git log --author="name"
git log --since="2023-01-01"
git log --until="2023-12-31"
git log -S "text"

# File history
git log --follow <file>
git blame <file>
```

### Diff Commands
```bash
# Show differences
git diff
git diff --staged
git diff <commit>
git diff <commit1> <commit2>

# Show file changes
git diff --name-only
git diff --stat
```

### Show Commands
```bash
# Show commit
git show <commit>
git show <commit> --name-only

# Show branch
git show-branch

# Show object
git show <object>
```

## ⚙️ Configuration

### Global Configuration
```bash
# User identity
git config --global user.name "Your Name"
git config --global user.email "email@example.com"

# Editor
git config --global core.editor "code --wait"

# Default branch
git config --global init.defaultBranch main

# Line endings
git config --global core.autocrlf input  # Linux/macOS
git config --global core.autocrlf true   # Windows
```

### Repository Configuration
```bash
# Local user (overrides global)
git config user.name "Project Name"
git config user.email "project@example.com"

# Branch configuration
git config branch.main.mergeoptions --no-ff

# Remote configuration
git config remote.origin.fetch "+refs/heads/*:refs/remotes/origin/*"
```

### Aliases
```bash
# Common aliases
git config --global alias.co checkout
git config --global alias.br branch
git config --global alias.ci commit
git config --global alias.st status
git config --global alias.unstage 'reset HEAD --'

# Advanced aliases
git config --global alias.lg "log --color --graph --pretty=format:'%Cred%h%Creset -%C(yellow)%d%Creset %s %Cgreen(%cr) %C(bold blue)<%an>%Creset' --abbrev-commit"
git config --global alias.stats "log --stat --oneline"
```

## 🔧 Advanced Features

### Submodules
```bash
# Add submodule
git submodule add <url> <path>

# Initialize submodules
git submodule init
git submodule update

# Update submodules
git submodule update --remote

# Remove submodule
git submodule deinit <path>
git rm <path>
```

### Subtrees
```bash
# Add subtree
git subtree add --prefix=<path> <url> <branch> --squash

# Pull from subtree
git subtree pull --prefix=<path> <url> <branch> --squash

# Push to subtree
git subtree push --prefix=<path> <url> <branch>
```

### Bisect
```bash
# Start bisect
git bisect start
git bisect bad <commit>
git bisect good <commit>

# Mark current commit
git bisect good
git bisect bad

# Run automated bisect
git bisect run <command>

# Reset bisect
git bisect reset
```

## 🛠️ Plumbing Commands

### Object Inspection
```bash
# Show object type
git cat-file -t <object>

# Show object size
git cat-file -s <object>

# Show object content
git cat-file -p <object>

# Hash object
git hash-object <file>
git hash-object -w <file>
```

### Tree and Commit
```bash
# List tree
git ls-tree <tree>
git ls-tree -r <tree>

# Write tree
git write-tree

# Create commit
git commit-tree <tree> -p <parent> -m "message"
```

### References
```bash
# Update reference
git update-ref refs/heads/<branch> <commit>

# Show reference
git show-ref

# List references
git for-each-ref --format='%(refname)'
```

## 🔍 Troubleshooting

### Recovery Commands
```bash
# Reflog
git reflog
git reflog show <ref>

# Recover from reflog
git checkout <commit>
git reset --hard <commit>

# Find lost commits
git fsck --lost-found
```

### Cleanup Commands
```bash
# Garbage collection
git gc
git gc --aggressive

# Prune objects
git prune

# Clean working directory
git clean -n  # Dry run
git clean -f  # Force
git clean -fd # Include directories
```

### Performance
```bash
# Count objects
git count-objects -v

# Verify pack
git verify-pack -v .git/objects/pack/*.idx

# Repack
git repack -a -d
git repack -a -d -f --depth=250 --window=250
```

## 📋 Common Workflows

### Feature Development
```bash
# Start feature
git checkout -b feature/new-feature
# ... make changes ...
git add .
git commit -m "feat: add new feature"
git push -u origin feature/new-feature

# Create PR, then merge
git checkout main
git pull origin main
git merge feature/new-feature
git push origin main
git branch -d feature/new-feature
```

### Hotfix Workflow
```bash
# Create hotfix
git checkout -b hotfix/urgent-fix
# ... fix issue ...
git add .
git commit -m "fix: resolve urgent issue"
git push -u origin hotfix/urgent-fix

# Merge to main and develop
git checkout main
git merge hotfix/urgent-fix
git push origin main

git checkout develop
git merge hotfix/urgent-fix
git push origin develop

# Cleanup
git branch -d hotfix/urgent-fix
```

### Release Workflow
```bash
# Create release branch
git checkout -b release/v1.2.0
# ... final adjustments ...
git add .
git commit -m "chore: prepare release v1.2.0"
git tag -a v1.2.0 -m "Release v1.2.0"

# Merge to main
git checkout main
git merge release/v1.2.0
git push origin main
git push origin v1.2.0

# Merge to develop
git checkout develop
git merge release/v1.2.0
git push origin develop

# Cleanup
git branch -d release/v1.2.0
```

## 🎯 Best Practices

### Commit Messages
```
type(scope): description

feat: add user authentication
fix: resolve login bug
docs: update README
style: format code
refactor: restructure components
test: add unit tests
chore: update dependencies
```

### Branch Naming
```
feature/user-authentication
bugfix/login-error
hotfix/security-patch
release/v1.2.0
chore/update-dependencies
```

### File Organization
```
.gitignore
README.md
CONTRIBUTING.md
CODE_OF_CONDUCT.md
docs/
src/
tests/
.github/
```

## 🔗 Useful Resources

- [Git Documentation](https://git-scm.com/doc)
- [GitHub Guides](https://guides.github.com/)
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)
- [Interactive Git Tutorial](https://learngitbranching.js.org/)
- [Conventional Commits](https://www.conventionalcommits.org/)

---

**Remember**: This reference covers the most common Git operations. For advanced usage, refer to the full Git documentation and the comprehensive course materials.