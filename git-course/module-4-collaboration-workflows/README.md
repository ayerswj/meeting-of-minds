# Module 4: Collaboration and Workflows

## 🎯 Learning Objectives

By the end of this module, you will be able to:
- Collaborate effectively in team environments
- Implement pull request workflows
- Conduct effective code reviews
- Work with forked repositories
- Understand different collaboration models
- Manage team permissions and access

## 📚 Topics Covered

### 1. Team Collaboration Best Practices

#### Repository Organization

**Repository Structure:**
```
project/
├── .github/
│   ├── ISSUE_TEMPLATE/
│   ├── PULL_REQUEST_TEMPLATE.md
│   └── workflows/
├── docs/
├── src/
├── tests/
├── .gitignore
├── README.md
├── CONTRIBUTING.md
└── CODE_OF_CONDUCT.md
```

**Essential Files:**
```markdown
# README.md
- Project description
- Installation instructions
- Usage examples
- Contributing guidelines
- License information

# CONTRIBUTING.md
- How to contribute
- Code style guidelines
- Testing requirements
- Pull request process

# CODE_OF_CONDUCT.md
- Community guidelines
- Expected behavior
- Reporting procedures
```

#### Branch Protection Rules

**GitHub Branch Protection:**
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

**Local Branch Protection:**
```bash
# Prevent direct commits to main
git config branch.main.mergeoptions --no-ff

# Set up pre-commit hooks
# (covered in Module 3)
```

### 2. Pull Requests and Code Reviews

#### Creating Effective Pull Requests

**Pull Request Template:**
```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests pass
- [ ] Integration tests pass
- [ ] Manual testing completed

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Documentation updated
- [ ] No breaking changes

## Screenshots (if applicable)
```

**Pull Request Workflow:**
```bash
# Create feature branch
git checkout -b feature/new-feature

# Make changes and commit
git add .
git commit -m "feat: add new feature"

# Push and create PR
git push -u origin feature/new-feature

# Create PR on GitHub with:
# - Clear title and description
# - Link to related issues
# - Request specific reviewers
# - Add appropriate labels
```

#### Code Review Best Practices

**For Reviewers:**
- Review code, not the person
- Be constructive and specific
- Focus on functionality and maintainability
- Check for security issues
- Verify tests and documentation

**For Authors:**
- Keep PRs small and focused
- Respond to feedback promptly
- Update PR based on review comments
- Test changes thoroughly
- Update documentation

**Review Checklist:**
```markdown
## Code Review Checklist

### Functionality
- [ ] Does the code work as intended?
- [ ] Are edge cases handled?
- [ ] Are error conditions handled?

### Code Quality
- [ ] Is the code readable and well-structured?
- [ ] Are naming conventions followed?
- [ ] Is there appropriate documentation?

### Security
- [ ] Are there any security vulnerabilities?
- [ ] Is input validation implemented?
- [ ] Are sensitive data handled properly?

### Performance
- [ ] Are there performance implications?
- [ ] Is the code efficient?
- [ ] Are there memory leaks?

### Testing
- [ ] Are tests included?
- [ ] Do tests cover edge cases?
- [ ] Are tests meaningful and maintainable?
```

### 3. Git Flow and GitHub Flow

#### Git Flow (Enterprise)

**Branch Strategy:**
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

# Feature development
git flow feature start user-authentication
# ... make changes ...
git flow feature finish user-authentication

# Release preparation
git flow release start 1.2.0
# ... final adjustments ...
git flow release finish 1.2.0

# Hotfix
git flow hotfix start security-patch
# ... fix ...
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
# Start feature
git checkout -b feature-name
git add .
git commit -m "feat: add new feature"
git push -u origin feature-name

# Create PR, review, merge
# Then deploy immediately
```

#### Trunk-Based Development

**Key Principles:**
- Single main branch
- Short-lived feature branches
- Continuous integration
- Small, frequent commits

**Workflow:**
```bash
# Always work from latest main
git checkout main
git pull origin main

# Create short-lived branch
git checkout -b feature/quick-fix
git add .
git commit -m "fix: resolve issue"
git push -u origin feature/quick-fix

# Create PR, review, merge quickly
```

### 4. Fork and Contribution Workflows

#### Forking Workflow

**Forking Process:**
```bash
# 1. Fork repository on GitHub
# 2. Clone your fork
git clone https://github.com/yourusername/project.git
cd project

# 3. Add upstream remote
git remote add upstream https://github.com/original/project.git

# 4. Create feature branch
git checkout -b feature/contribution
```

**Keeping Fork Updated:**
```bash
# Fetch upstream changes
git fetch upstream

# Merge upstream main into your main
git checkout main
git merge upstream/main

# Push to your fork
git push origin main
```

**Submitting Contributions:**
```bash
# Make changes in feature branch
git add .
git commit -m "feat: add new feature"
git push origin feature/contribution

# Create PR from your fork to upstream
# Wait for review and merge
```

#### Contributing to Open Source

**Before Contributing:**
1. Read the project's contributing guidelines
2. Check existing issues and PRs
3. Understand the project's coding standards
4. Set up development environment

**Contribution Process:**
```bash
# Fork and clone
git clone https://github.com/yourusername/project.git
cd project

# Create feature branch
git checkout -b fix/issue-description

# Make changes
# Write tests
# Update documentation

# Commit with conventional format
git commit -m "fix: resolve issue #123"

# Push and create PR
git push -u origin fix/issue-description
```

### 5. Team Management and Permissions

#### Repository Permissions

**GitHub Team Roles:**
- **Admin**: Full access to repository
- **Maintain**: Manage issues, PRs, and repository settings
- **Write**: Push code and manage issues/PRs
- **Read**: View and clone repository
- **Triage**: Manage issues and PRs without write access

**Setting Up Teams:**
```bash
# Create team on GitHub
# Add members to team
# Assign team to repository with appropriate role
```

#### Code Owners

**CODEOWNERS File:**
```bash
# .github/CODEOWNERS

# Global owners
* @project-admin

# Frontend files
*.js @frontend-team
*.css @frontend-team
*.html @frontend-team

# Backend files
*.py @backend-team
*.java @backend-team

# Documentation
docs/ @docs-team
*.md @docs-team

# Configuration
.github/ @devops-team
```

#### Issue and PR Templates

**Issue Template:**
```markdown
<!-- .github/ISSUE_TEMPLATE/bug_report.md -->
---
name: Bug report
about: Create a report to help us improve
title: ''
labels: bug
assignees: ''

---

**Describe the bug**
A clear and concise description of what the bug is.

**To Reproduce**
Steps to reproduce the behavior:
1. Go to '...'
2. Click on '....'
3. Scroll down to '....'
4. See error

**Expected behavior**
A clear and concise description of what you expected to happen.

**Screenshots**
If applicable, add screenshots to help explain your problem.

**Environment:**
 - OS: [e.g. macOS]
 - Browser: [e.g. chrome, safari]
 - Version: [e.g. 22]

**Additional context**
Add any other context about the problem here.
```

### 6. Continuous Integration and Deployment

#### GitHub Actions

**Basic CI Workflow:**
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

**Advanced CI/CD:**
```yaml
# .github/workflows/deploy.yml
name: Deploy

on:
  push:
    tags:
      - 'v*'

jobs:
  deploy:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Deploy to production
      run: |
        echo "Deploying version ${{ github.ref_name }}"
        # Add deployment commands here
```

## 🛠️ Hands-On Exercises

### Exercise 1: Team Collaboration Setup
1. Create a repository with proper structure
2. Set up branch protection rules
3. Create issue and PR templates
4. Set up CODEOWNERS file
5. Configure team permissions

### Exercise 2: Pull Request Workflow
1. Create a feature branch
2. Make changes and commit
3. Create a pull request with template
4. Conduct a code review
5. Merge the pull request

### Exercise 3: Fork and Contribute
1. Fork an open source project
2. Clone your fork locally
3. Make a contribution
4. Submit a pull request
5. Handle review feedback

### Exercise 4: CI/CD Pipeline
1. Set up GitHub Actions workflow
2. Configure automated testing
3. Set up deployment pipeline
4. Test the complete workflow

## 📝 Practice Project: Team Collaboration Platform

Create a collaboration platform with team workflows:

1. **Repository Setup**
   - Create repository with proper structure
   - Set up branch protection
   - Configure team permissions

2. **Feature Development**
   - Multiple team members work on features
   - Use pull request workflow
   - Conduct code reviews

3. **Release Management**
   - Implement Git Flow
   - Create release branches
   - Deploy with CI/CD

4. **Documentation**
   - Maintain up-to-date documentation
   - Use issue templates
   - Track project progress

## ❓ Assessment Questions

1. What are the key differences between Git Flow and GitHub Flow?
2. How do you set up branch protection rules?
3. What makes an effective pull request?
4. How do you keep a forked repository updated?
5. What are the different GitHub team roles?
6. How do you implement continuous integration with Git?
7. What is the purpose of a CODEOWNERS file?

## 🔗 Additional Resources

- [GitHub Flow](https://guides.github.com/introduction/flow/)
- [Git Flow Documentation](https://nvie.com/posts/a-successful-git-branching-model/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [Open Source Guide](https://opensource.guide/)

## 🎯 Next Steps

Once you've completed this module:
- Practice team collaboration workflows
- Contribute to open source projects
- Set up CI/CD pipelines
- Move on to **Module 5: Git Internals and Advanced Topics**

---

**Collaboration Tip**: Effective collaboration is about communication and process. Focus on clear communication and consistent workflows rather than just technical skills.