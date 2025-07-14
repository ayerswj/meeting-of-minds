# Module 2: Core Git Operations

## 🎯 Learning Objectives

By the end of this module, you will be able to:
- Work effectively with commits and commit history
- Create and manage branches
- Merge branches and resolve conflicts
- Work with remote repositories
- Understand Git's branching model
- Use advanced commit techniques

## 📚 Topics Covered

### 1. Working with Commits

#### Commit Best Practices

**Writing Good Commit Messages:**
```
feat: add user authentication system

- Implement JWT token-based authentication
- Add login/logout functionality
- Create user profile management
- Add password reset feature

Closes #123
```

**Commit Message Format:**
- **Type**: feat, fix, docs, style, refactor, test, chore
- **Subject**: Brief description (50 chars or less)
- **Body**: Detailed explanation (optional)
- **Footer**: References to issues (optional)

#### Advanced Commit Commands

**Amending Commits:**
```bash
# Amend the last commit
git commit --amend -m "Updated commit message"

# Add forgotten files to last commit
git add forgotten-file.txt
git commit --amend --no-edit
```

**Interactive Rebase:**
```bash
# Rebase last 3 commits
git rebase -i HEAD~3

# Available commands:
# pick - use commit
# reword - use commit, but edit the commit message
# edit - use commit, but stop for amending
# squash - use commit, but meld into previous commit
# fixup - like "squash", but discard this commit's log message
# exec - run command (the rest of the line) using shell
# drop - remove commit
```

**Commit History Navigation:**
```bash
# View specific commit
git show <commit-hash>

# View file history
git log --follow filename.txt

# View changes in specific commit
git show <commit-hash> --name-only

# Find commits by author
git log --author="John Doe"

# Find commits containing specific text
git log -S "function name"
```

### 2. Branching Basics

#### Understanding Branches

A branch in Git is simply a lightweight movable pointer to a commit. The default branch is usually called `main` or `master`.

**Branch Operations:**
```bash
# List all branches
git branch

# Create new branch
git branch feature-branch

# Create and switch to new branch
git checkout -b feature-branch
# or (Git 2.23+)
git switch -c feature-branch

# Switch between branches
git checkout main
git switch main

# Delete branch
git branch -d feature-branch  # Safe delete
git branch -D feature-branch  # Force delete

# Rename current branch
git branch -m new-branch-name
```

#### Branch Management

**Viewing Branch Information:**
```bash
# Show current branch
git branch

# Show all branches (local and remote)
git branch -a

# Show merged branches
git branch --merged

# Show unmerged branches
git branch --no-merged
```

**Branch Workflow:**
```bash
# Start new feature
git checkout -b feature/user-authentication

# Make changes and commit
git add .
git commit -m "feat: add user authentication"

# Switch back to main
git checkout main

# Update main with latest changes
git pull origin main

# Merge feature branch
git merge feature/user-authentication
```

### 3. Merging and Conflict Resolution

#### Types of Merges

**Fast-Forward Merge:**
- Occurs when there are no new commits on the base branch
- Git simply moves the pointer forward

**Three-Way Merge:**
- Creates a merge commit when branches have diverged
- Combines changes from both branches

**Merge Commands:**
```bash
# Merge branch into current branch
git merge feature-branch

# Merge with specific strategy
git merge -s recursive feature-branch
git merge -s octopus branch1 branch2

# Abort merge
git merge --abort

# Continue merge after resolving conflicts
git merge --continue
```

#### Conflict Resolution

**When Conflicts Occur:**
1. Git stops the merge process
2. Files with conflicts are marked
3. You must manually resolve conflicts
4. Stage resolved files
5. Complete the merge

**Conflict Markers:**
```
<<<<<<< HEAD
// Your current branch changes
=======
// Incoming branch changes
>>>>>>> feature-branch
```

**Resolving Conflicts:**
```bash
# 1. Open conflicted files and resolve manually
# 2. Stage resolved files
git add resolved-file.txt

# 3. Complete the merge
git commit
# or
git merge --continue
```

**Conflict Resolution Tools:**
```bash
# Use mergetool
git mergetool

# Configure mergetool
git config --global merge.tool vimdiff
git config --global merge.tool kdiff3
git config --global merge.tool meld
```

### 4. Remote Repositories

#### Working with Remotes

**Remote Repository Setup:**
```bash
# Add remote repository
git remote add origin https://github.com/username/repo.git

# List remotes
git remote -v

# Remove remote
git remote remove origin

# Rename remote
git remote rename origin upstream
```

**Fetching and Pulling:**
```bash
# Fetch updates from remote
git fetch origin

# Fetch specific branch
git fetch origin main

# Pull changes (fetch + merge)
git pull origin main

# Pull with rebase
git pull --rebase origin main
```

**Pushing Changes:**
```bash
# Push current branch to remote
git push origin main

# Push new branch to remote
git push -u origin feature-branch

# Push all branches
git push --all origin

# Force push (use with caution!)
git push --force origin main
```

#### Remote Branch Management

**Tracking Remote Branches:**
```bash
# Set up tracking
git branch --set-upstream-to=origin/main main

# Create local branch tracking remote
git checkout -b feature-branch origin/feature-branch

# Update remote tracking branches
git remote update
```

**Working with Remote Branches:**
```bash
# List remote branches
git branch -r

# Delete remote branch
git push origin --delete feature-branch

# Prune deleted remote branches
git remote prune origin
```

### 5. Advanced Branching Strategies

#### Branch Naming Conventions

**Common Patterns:**
- `feature/user-authentication` - New features
- `bugfix/login-error` - Bug fixes
- `hotfix/security-patch` - Critical fixes
- `release/v1.2.0` - Release preparation
- `chore/update-dependencies` - Maintenance tasks

#### Branch Protection

**GitHub Branch Protection:**
- Require pull request reviews
- Require status checks to pass
- Require branches to be up to date
- Restrict pushes to matching branches

**Local Branch Protection:**
```bash
# Prevent direct commits to main
git config branch.main.mergeoptions --no-ff

# Set up pre-commit hooks
# (covered in Module 3)
```

## 🛠️ Hands-On Exercises

### Exercise 1: Branching Workflow
1. Create a new repository
2. Create a main branch with initial content
3. Create a feature branch
4. Make changes in the feature branch
5. Merge the feature branch back to main

### Exercise 2: Conflict Resolution
1. Create two branches from the same commit
2. Modify the same file in both branches
3. Try to merge and resolve the conflict
4. Complete the merge

### Exercise 3: Remote Repository Workflow
1. Create a repository on GitHub
2. Clone it locally
3. Make changes and push them
4. Create a pull request
5. Merge the pull request

### Exercise 4: Advanced Commits
1. Make several commits
2. Use interactive rebase to squash commits
3. Amend a commit message
4. View commit history with different formats

## 📝 Practice Project: Blog System

Create a simple blog system with multiple features:

1. **Setup**
   ```bash
   git init blog-system
   cd blog-system
   git remote add origin https://github.com/yourusername/blog-system.git
   ```

2. **Feature Branches**
   - `feature/user-management` - User registration/login
   - `feature/post-creation` - Create and edit posts
   - `feature/comments` - Comment system
   - `feature/styling` - CSS and UI improvements

3. **Development Workflow**
   - Work on each feature in separate branches
   - Create pull requests for each feature
   - Merge features into main branch
   - Handle conflicts when they arise

## ❓ Assessment Questions

1. What is the difference between a fast-forward merge and a three-way merge?
2. How do you resolve a merge conflict?
3. What is the purpose of `git fetch` vs `git pull`?
4. Explain the difference between `git merge` and `git rebase`.
5. How do you set up tracking between local and remote branches?
6. What are the best practices for writing commit messages?
7. How do you amend the last commit?

## 🔗 Additional Resources

- [Git Branching and Merging](https://git-scm.com/book/en/v2/Git-Branching-Branches-in-a-Nutshell)
- [GitHub Flow](https://guides.github.com/introduction/flow/)
- [Conventional Commits](https://www.conventionalcommits.org/)

## 🎯 Next Steps

Once you've completed this module:
- Practice branching and merging extensively
- Work with remote repositories
- Complete the practice project
- Move on to **Module 3: Advanced Git Features**

---

**Pro Tip**: The key to mastering Git is practice. Create test repositories and experiment with different scenarios to build confidence.