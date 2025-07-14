# Module 3: Advanced Git Features

## 🎯 Learning Objectives

By the end of this module, you will be able to:
- Implement advanced branching strategies
- Use Git hooks for automation
- Master stashing and cherry-picking
- Work with Git tags and releases
- Understand Git's internal structure
- Use advanced Git workflows

## 📚 Topics Covered

### 1. Advanced Branching Strategies

#### Git Flow Workflow

**Branch Structure:**
```
main (production)
├── develop (integration)
├── feature/user-auth
├── feature/payment
├── release/v1.2.0
└── hotfix/security-patch
```

**Git Flow Commands:**
```bash
# Initialize Git Flow
git flow init

# Start feature branch
git flow feature start user-authentication

# Finish feature branch
git flow feature finish user-authentication

# Start release branch
git flow release start 1.2.0

# Finish release branch
git flow release finish 1.2.0

# Start hotfix branch
git flow hotfix start security-patch

# Finish hotfix branch
git flow hotfix finish security-patch
```

#### GitHub Flow (Simplified)

**Principles:**
1. Branch from main
2. Make changes
3. Create pull request
4. Review and discuss
5. Merge to main
6. Deploy immediately

**Workflow:**
```bash
# Create feature branch
git checkout -b feature-name

# Make changes and commit
git add .
git commit -m "feat: add new feature"

# Push and create PR
git push -u origin feature-name
# Create PR on GitHub

# After review, merge and delete branch
git checkout main
git pull origin main
git branch -d feature-name
```

#### Trunk-Based Development

**Key Principles:**
- Single main branch (trunk)
- Short-lived feature branches
- Continuous integration
- Small, frequent commits

**Workflow:**
```bash
# Always start from latest main
git checkout main
git pull origin main

# Create short-lived feature branch
git checkout -b feature/quick-fix

# Make small, focused commits
git add .
git commit -m "fix: resolve login issue"

# Push and create PR immediately
git push -u origin feature/quick-fix
```

### 2. Git Hooks and Automation

#### Understanding Git Hooks

Git hooks are scripts that run automatically before or after Git commands. They're stored in `.git/hooks/`.

**Hook Types:**
- **Pre-commit**: Runs before commit
- **Post-commit**: Runs after commit
- **Pre-push**: Runs before push
- **Pre-receive**: Runs on server before receiving push
- **Post-receive**: Runs on server after receiving push

#### Creating Custom Hooks

**Pre-commit Hook Example:**
```bash
#!/bin/sh
# .git/hooks/pre-commit

# Run tests
npm test

# Check code style
npm run lint

# Check for TODO comments
if git diff --cached | grep -i "TODO"; then
    echo "Error: TODO comments found in staged files"
    exit 1
fi
```

**Post-commit Hook Example:**
```bash
#!/bin/sh
# .git/hooks/post-commit

# Send notification
echo "Commit $(git rev-parse HEAD) created successfully"

# Update deployment status
curl -X POST https://api.example.com/deploy/status \
  -d "commit=$(git rev-parse HEAD)&status=pending"
```

#### Using Husky for Node.js Projects

**Setup:**
```bash
npm install --save-dev husky

# Initialize husky
npx husky install

# Add pre-commit hook
npx husky add .husky/pre-commit "npm test"
npx husky add .husky/pre-commit "npm run lint"

# Add commit-msg hook
npx husky add .husky/commit-msg "npx --no -- commitlint --edit \$1"
```

**package.json Configuration:**
```json
{
  "scripts": {
    "prepare": "husky install"
  },
  "husky": {
    "hooks": {
      "pre-commit": "lint-staged",
      "commit-msg": "commitlint -E HUSKY_GIT_PARAMS"
    }
  }
}
```

### 3. Stashing and Cherry-picking

#### Git Stash

**Basic Stashing:**
```bash
# Stash current changes
git stash

# Stash with message
git stash push -m "WIP: working on feature"

# List stashes
git stash list

# Apply most recent stash
git stash pop

# Apply specific stash
git stash apply stash@{1}

# Drop stash
git stash drop stash@{0}

# Clear all stashes
git stash clear
```

**Advanced Stashing:**
```bash
# Stash specific files
git stash push -m "stash specific files" file1.txt file2.txt

# Stash untracked files
git stash push -u -m "stash untracked files"

# Stash including ignored files
git stash push -a -m "stash everything"

# Create branch from stash
git stash branch new-branch stash@{1}

# Show stash contents
git stash show -p stash@{0}
```

#### Cherry-picking

**Basic Cherry-pick:**
```bash
# Cherry-pick single commit
git cherry-pick abc1234

# Cherry-pick multiple commits
git cherry-pick abc1234 def5678

# Cherry-pick range of commits
git cherry-pick abc1234..def5678

# Cherry-pick without committing
git cherry-pick --no-commit abc1234
```

**Advanced Cherry-pick:**
```bash
# Cherry-pick with conflict resolution
git cherry-pick -X theirs abc1234

# Cherry-pick and sign commit
git cherry-pick -s abc1234

# Skip if already applied
git cherry-pick --skip

# Abort cherry-pick
git cherry-pick --abort
```

**Cherry-pick Workflow:**
```bash
# Find commits to cherry-pick
git log --oneline main..feature-branch

# Cherry-pick specific commits
git cherry-pick abc1234 def5678

# Resolve conflicts if any
# Complete cherry-pick
git cherry-pick --continue
```

### 4. Git Tags and Releases

#### Working with Tags

**Lightweight Tags:**
```bash
# Create lightweight tag
git tag v1.0.0

# Create tag for specific commit
git tag v1.0.0 abc1234

# List all tags
git tag

# Show tag details
git show v1.0.0

# Delete tag
git tag -d v1.0.0
```

**Annotated Tags:**
```bash
# Create annotated tag
git tag -a v1.0.0 -m "Release version 1.0.0"

# Create signed tag
git tag -s v1.0.0 -m "Signed release v1.0.0"

# Verify signed tag
git tag -v v1.0.0
```

**Tag Management:**
```bash
# Push specific tag
git push origin v1.0.0

# Push all tags
git push origin --tags

# Delete remote tag
git push origin --delete v1.0.0

# Checkout tag
git checkout v1.0.0

# Create branch from tag
git checkout -b release-v1.0.0 v1.0.0
```

#### Semantic Versioning

**Version Format: MAJOR.MINOR.PATCH**
- **MAJOR**: Incompatible API changes
- **MINOR**: New functionality (backward compatible)
- **PATCH**: Bug fixes (backward compatible)

**Versioning Workflow:**
```bash
# Create release branch
git checkout -b release/v1.2.0

# Make final adjustments
# Update version numbers
# Update changelog

# Create release commit
git commit -m "chore: prepare release v1.2.0"

# Create annotated tag
git tag -a v1.2.0 -m "Release v1.2.0"

# Merge to main
git checkout main
git merge release/v1.2.0

# Push release
git push origin main
git push origin v1.2.0

# Clean up
git branch -d release/v1.2.0
```

### 5. Advanced Git Workflows

#### Rebase Workflow

**Interactive Rebase:**
```bash
# Rebase last 5 commits
git rebase -i HEAD~5

# Rebase onto different base
git rebase main feature-branch

# Rebase with conflict resolution
git rebase -X theirs main
```

**Rebase vs Merge:**
```bash
# Merge workflow (preserves history)
git checkout main
git merge feature-branch

# Rebase workflow (linear history)
git checkout feature-branch
git rebase main
git checkout main
git merge feature-branch
```

#### Bisect for Bug Finding

**Using Git Bisect:**
```bash
# Start bisect
git bisect start

# Mark current version as bad
git bisect bad

# Mark known good version
git bisect good v1.0.0

# Test current version and mark
git bisect good  # or git bisect bad

# Continue until bug is found
git bisect reset
```

**Automated Bisect:**
```bash
# Run test script automatically
git bisect run npm test

# Custom test script
git bisect run ./test-script.sh
```

#### Submodules and Subtrees

**Git Submodules:**
```bash
# Add submodule
git submodule add https://github.com/user/library.git lib/library

# Clone repository with submodules
git clone --recursive https://github.com/user/project.git

# Update submodules
git submodule update --remote

# Remove submodule
git submodule deinit lib/library
git rm lib/library
git commit -m "Remove submodule"
```

**Git Subtrees:**
```bash
# Add subtree
git subtree add --prefix=lib/library https://github.com/user/library.git main --squash

# Update subtree
git subtree pull --prefix=lib/library https://github.com/user/library.git main --squash

# Push changes to subtree
git subtree push --prefix=lib/library https://github.com/user/library.git main
```

## 🛠️ Hands-On Exercises

### Exercise 1: Git Flow Implementation
1. Initialize Git Flow in a repository
2. Create feature branches using Git Flow
3. Create a release branch
4. Create a hotfix branch
5. Complete the Git Flow cycle

### Exercise 2: Custom Git Hooks
1. Create a pre-commit hook that runs tests
2. Create a post-commit hook that sends notifications
3. Test the hooks with commits
4. Disable and re-enable hooks

### Exercise 3: Stashing and Cherry-picking
1. Make changes and stash them
2. Switch branches and apply stashes
3. Cherry-pick commits between branches
4. Handle conflicts during cherry-pick

### Exercise 4: Tag Management
1. Create lightweight and annotated tags
2. Create a release workflow
3. Push tags to remote
4. Create branches from tags

## 📝 Practice Project: E-commerce Platform

Create an e-commerce platform with advanced Git workflows:

1. **Setup Git Flow**
   ```bash
   git flow init
   ```

2. **Feature Development**
   - `feature/user-management`
   - `feature/product-catalog`
   - `feature/shopping-cart`
   - `feature/payment-processing`

3. **Release Management**
   - Create release branches for each version
   - Use semantic versioning
   - Create annotated tags for releases

4. **Automation**
   - Set up pre-commit hooks for testing
   - Configure post-commit notifications
   - Implement automated deployment

## ❓ Assessment Questions

1. What is the difference between Git Flow and GitHub Flow?
2. How do you create and use Git hooks?
3. When would you use `git stash` vs `git cherry-pick`?
4. What is the difference between lightweight and annotated tags?
5. How do you use `git bisect` to find bugs?
6. What are the advantages of using Git submodules vs subtrees?
7. How do you implement semantic versioning with Git?

## 🔗 Additional Resources

- [Git Flow Documentation](https://nvie.com/posts/a-successful-git-branching-model/)
- [Git Hooks Documentation](https://git-scm.com/docs/githooks)
- [Semantic Versioning](https://semver.org/)
- [Git Submodules](https://git-scm.com/book/en/v2/Git-Tools-Submodules)

## 🎯 Next Steps

Once you've completed this module:
- Practice advanced workflows extensively
- Implement automation with hooks
- Work with complex branching strategies
- Move on to **Module 4: Collaboration and Workflows**

---

**Expert Tip**: Advanced Git features are powerful but can be complex. Always test workflows in a safe environment before using them in production.