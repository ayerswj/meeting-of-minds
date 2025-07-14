# Git Workflows Guide

A comprehensive guide to different Git workflows, branching strategies, and team collaboration patterns.

## 🎯 Understanding Git Workflows

A Git workflow is a recipe or recommendation for how to use Git to accomplish work in a consistent and productive manner. Different workflows are suited for different team sizes, project types, and organizational needs.

## 🌿 Git Flow (Enterprise Workflow)

### Overview
Git Flow is a robust workflow designed for projects with scheduled releases. It's ideal for enterprise environments with strict release management.

### Branch Structure
```
main (production)
├── develop (integration)
├── feature/user-auth
├── feature/payment
├── release/v1.2.0
└── hotfix/security-patch
```

### Branch Types

#### Main Branch
- **Purpose**: Production-ready code
- **Naming**: `main` or `master`
- **Protection**: Direct commits forbidden
- **Lifecycle**: Only receives merges from `develop` and `hotfix`

#### Develop Branch
- **Purpose**: Integration branch for features
- **Naming**: `develop`
- **Protection**: Direct commits allowed for integration
- **Lifecycle**: Receives merges from `feature` branches

#### Feature Branches
- **Purpose**: Development of new features
- **Naming**: `feature/feature-name`
- **Source**: `develop`
- **Target**: `develop`
- **Lifecycle**: Created from `develop`, merged back to `develop`

#### Release Branches
- **Purpose**: Preparation for production release
- **Naming**: `release/version-number`
- **Source**: `develop`
- **Target**: `main` and `develop`
- **Lifecycle**: Created from `develop`, merged to both `main` and `develop`

#### Hotfix Branches
- **Purpose**: Critical production fixes
- **Naming**: `hotfix/issue-description`
- **Source**: `main`
- **Target**: `main` and `develop`
- **Lifecycle**: Created from `main`, merged to both `main` and `develop`

### Git Flow Commands

#### Initialization
```bash
# Initialize Git Flow
git flow init

# Configure branch names (optional)
git flow init -d
```

#### Feature Development
```bash
# Start feature
git flow feature start user-authentication

# Work on feature
# ... make changes ...
git add .
git commit -m "feat: add user authentication"

# Finish feature
git flow feature finish user-authentication
```

#### Release Management
```bash
# Start release
git flow release start 1.2.0

# Make release adjustments
# ... update version numbers, changelog, etc. ...
git add .
git commit -m "chore: prepare release v1.2.0"

# Finish release
git flow release finish 1.2.0
```

#### Hotfix Management
```bash
# Start hotfix
git flow hotfix start security-patch

# Fix the issue
# ... make critical fixes ...
git add .
git commit -m "fix: resolve security vulnerability"

# Finish hotfix
git flow hotfix finish security-patch
```

### Git Flow Workflow Example

```bash
# 1. Start new feature
git flow feature start user-login

# 2. Develop feature
echo "function login() { ... }" > auth.js
git add auth.js
git commit -m "feat: implement user login"

# 3. Finish feature
git flow feature finish user-login

# 4. Start release
git flow release start 1.0.0

# 5. Prepare release
echo "v1.0.0" > VERSION
git add VERSION
git commit -m "chore: bump version to 1.0.0"

# 6. Finish release
git flow release finish 1.0.0

# 7. Handle hotfix
git flow hotfix start critical-bug
# ... fix bug ...
git flow hotfix finish critical-bug
```

### Advantages
- Clear separation of concerns
- Stable production code
- Organized release management
- Support for emergency fixes

### Disadvantages
- Complex for small teams
- Overhead for simple projects
- Multiple long-lived branches
- Complex merge scenarios

## 🚀 GitHub Flow (Simplified Workflow)

### Overview
GitHub Flow is a lightweight, branch-based workflow that supports teams and projects that deploy regularly. It's ideal for continuous deployment environments.

### Branch Structure
```
main (production)
├── feature/new-feature
├── feature/bug-fix
└── feature/improvement
```

### Principles
1. **Branch from main**: Create feature branches from main
2. **Make changes**: Work on your feature
3. **Create pull request**: Open PR for discussion
4. **Review and discuss**: Get feedback from team
5. **Merge to main**: Merge after approval
6. **Deploy immediately**: Deploy to production

### GitHub Flow Commands

#### Feature Development
```bash
# Create feature branch
git checkout -b feature/new-feature

# Make changes
# ... work on feature ...
git add .
git commit -m "feat: add new feature"

# Push and create PR
git push -u origin feature/new-feature
```

#### Code Review Process
```bash
# After PR approval, merge to main
git checkout main
git pull origin main
git merge feature/new-feature

# Deploy immediately
git push origin main
# ... trigger deployment ...

# Clean up
git branch -d feature/new-feature
```

### GitHub Flow Example

```bash
# 1. Start feature
git checkout -b feature/user-profile
git pull origin main

# 2. Develop feature
echo "function getUserProfile() { ... }" > profile.js
git add profile.js
git commit -m "feat: add user profile functionality"

# 3. Push and create PR
git push -u origin feature/user-profile

# 4. After review, merge
git checkout main
git pull origin main
git merge feature/user-profile
git push origin main

# 5. Deploy
# ... deployment script ...

# 6. Cleanup
git branch -d feature/user-profile
```

### Advantages
- Simple and straightforward
- Supports continuous deployment
- Quick feedback cycles
- Easy to understand

### Disadvantages
- Less structured than Git Flow
- May not suit complex release management
- Requires good testing practices
- Can become chaotic with large teams

## 🌳 Trunk-Based Development

### Overview
Trunk-Based Development is a source-control branching model where developers collaborate on code in a single branch called "trunk" (main/master). It's ideal for teams practicing continuous integration.

### Branch Structure
```
main (trunk)
├── feature/quick-fix (short-lived)
└── feature/small-feature (short-lived)
```

### Principles
- **Single main branch**: All development happens on main
- **Short-lived feature branches**: Branches exist for hours, not days
- **Continuous integration**: Code is integrated frequently
- **Small commits**: Frequent, small commits
- **Immediate integration**: Merge to main as soon as possible

### Trunk-Based Development Commands

#### Feature Development
```bash
# Create short-lived branch
git checkout -b feature/quick-fix

# Make small, focused changes
git add .
git commit -m "fix: resolve login issue"

# Push and create PR immediately
git push -u origin feature/quick-fix
```

#### Integration
```bash
# After quick review, merge to main
git checkout main
git pull origin main
git merge feature/quick-fix
git push origin main

# Clean up immediately
git branch -d feature/quick-fix
```

### Trunk-Based Development Example

```bash
# 1. Start from latest main
git checkout main
git pull origin main

# 2. Create short-lived branch
git checkout -b fix/button-styling

# 3. Make focused change
echo ".button { color: blue; }" >> styles.css
git add styles.css
git commit -m "fix: update button color"

# 4. Push and create PR
git push -u origin fix/button-styling

# 5. Quick review and merge
git checkout main
git pull origin main
git merge fix/button-styling
git push origin main

# 6. Cleanup
git branch -d fix/button-styling
```

### Advantages
- Simple branching model
- Encourages continuous integration
- Reduces merge conflicts
- Fast feedback cycles

### Disadvantages
- Requires excellent testing
- May not suit complex features
- Requires mature development practices
- Can be challenging for large teams

## 🔄 Forking Workflow

### Overview
The Forking Workflow is commonly used in open source projects. Each developer has their own public repository (fork) and contributes via pull requests.

### Repository Structure
```
Original Repository (upstream)
├── Developer A's Fork
│   └── feature-branch
├── Developer B's Fork
│   └── feature-branch
└── Developer C's Fork
    └── feature-branch
```

### Forking Workflow Commands

#### Setup
```bash
# Fork repository on GitHub
# Clone your fork
git clone https://github.com/yourusername/project.git
cd project

# Add upstream remote
git remote add upstream https://github.com/original/project.git
```

#### Development
```bash
# Create feature branch
git checkout -b feature/contribution

# Make changes
# ... work on feature ...
git add .
git commit -m "feat: add new feature"

# Push to your fork
git push -u origin feature/contribution
```

#### Keeping Fork Updated
```bash
# Fetch upstream changes
git fetch upstream

# Merge upstream main into your main
git checkout main
git merge upstream/main

# Push to your fork
git push origin main
```

#### Contributing
```bash
# Create PR from your fork to upstream
# Wait for review and merge
# Update your fork after merge
git checkout main
git pull upstream main
git push origin main
```

### Forking Workflow Example

```bash
# 1. Fork and clone
git clone https://github.com/yourusername/project.git
cd project
git remote add upstream https://github.com/original/project.git

# 2. Create feature branch
git checkout -b feature/documentation

# 3. Make changes
echo "# New Documentation" >> README.md
git add README.md
git commit -m "docs: add new documentation"

# 4. Push to fork
git push -u origin feature/documentation

# 5. Create PR on GitHub
# ... create PR from your fork to upstream ...

# 6. After merge, update fork
git checkout main
git pull upstream main
git push origin main
```

### Advantages
- Isolated development environment
- No direct access to original repository
- Suitable for open source contribution
- Clear contribution process

### Disadvantages
- More complex setup
- Requires manual synchronization
- May have outdated forks
- More steps for simple changes

## 🏢 Enterprise Workflows

### Centralized Workflow
A workflow where all developers work directly on the main branch. Suitable for small teams with simple projects.

```bash
# All work happens on main
git checkout main
git pull origin main

# Make changes directly
# ... work on feature ...
git add .
git commit -m "feat: add feature"

# Push directly
git push origin main
```

### Feature Branch Workflow
A workflow where all feature development takes place in dedicated branches. Suitable for teams that want to isolate work.

```bash
# Create feature branch
git checkout -b feature/new-feature

# Develop feature
# ... work on feature ...
git add .
git commit -m "feat: implement feature"

# Merge to main
git checkout main
git pull origin main
git merge feature/new-feature
git push origin main

# Clean up
git branch -d feature/new-feature
```

## 🔧 Workflow Configuration

### Branch Protection Rules

#### GitHub Branch Protection
```yaml
# .github/branch-protection.yml
branches:
  - name: main
    protection:
      required_status_checks:
        strict: true
        contexts:
          - ci/tests
          - ci/lint
      enforce_admins: true
      required_pull_request_reviews:
        required_approving_review_count: 2
        dismiss_stale_reviews: true
        require_code_owner_reviews: true
      restrictions:
        users: []
        teams: []
```

#### GitLab Branch Protection
```yaml
# .gitlab-ci.yml
protected_branches:
  - name: main
    push_access_levels:
      - 40  # Maintainer
    merge_access_levels:
      - 40  # Maintainer
    unprotect_access_levels:
      - 50  # Owner
```

### Automated Workflows

#### GitHub Actions
```yaml
# .github/workflows/ci.yml
name: CI

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Setup Node.js
      uses: actions/setup-node@v3
      with:
        node-version: '18'
    - name: Install dependencies
      run: npm ci
    - name: Run tests
      run: npm test
    - name: Run linting
      run: npm run lint
```

#### GitLab CI/CD
```yaml
# .gitlab-ci.yml
stages:
  - test
  - build
  - deploy

test:
  stage: test
  script:
    - npm install
    - npm test
    - npm run lint

build:
  stage: build
  script:
    - npm run build
  only:
    - main

deploy:
  stage: deploy
  script:
    - echo "Deploying to production"
  only:
    - main
```

## 📊 Workflow Comparison

| Workflow | Team Size | Complexity | Release Frequency | Best For |
|----------|-----------|------------|-------------------|----------|
| Git Flow | Large | High | Scheduled | Enterprise |
| GitHub Flow | Medium | Low | Continuous | Web apps |
| Trunk-Based | Small-Medium | Low | Continuous | Agile teams |
| Forking | Any | Medium | Variable | Open source |

## 🎯 Choosing the Right Workflow

### Factors to Consider

#### Team Size
- **Small teams (1-5)**: GitHub Flow, Trunk-Based
- **Medium teams (6-20)**: GitHub Flow, Git Flow
- **Large teams (20+)**: Git Flow, Enterprise workflows

#### Project Type
- **Web applications**: GitHub Flow, Trunk-Based
- **Mobile apps**: Git Flow
- **Libraries**: Forking Workflow
- **Enterprise software**: Git Flow

#### Release Frequency
- **Continuous deployment**: GitHub Flow, Trunk-Based
- **Scheduled releases**: Git Flow
- **On-demand releases**: Any workflow

#### Team Maturity
- **Novice teams**: GitHub Flow
- **Experienced teams**: Any workflow
- **Expert teams**: Trunk-Based, Custom workflows

### Migration Strategies

#### From Git Flow to GitHub Flow
```bash
# 1. Stop creating feature branches
# 2. Work directly on develop
# 3. Merge develop to main frequently
# 4. Eventually work directly on main
```

#### From GitHub Flow to Trunk-Based
```bash
# 1. Reduce feature branch lifetime
# 2. Increase integration frequency
# 3. Improve testing practices
# 4. Work directly on main
```

## 📚 Resources

- [Git Flow Documentation](https://nvie.com/posts/a-successful-git-branching-model/)
- [GitHub Flow Guide](https://guides.github.com/introduction/flow/)
- [Trunk-Based Development](https://trunkbaseddevelopment.com/)
- [Git Workflows Comparison](https://git-scm.com/book/en/v2/Distributed-Git-Distributed-Workflows)

---

**Remember**: The best workflow is the one that your team can follow consistently. Start simple and evolve as your needs grow.