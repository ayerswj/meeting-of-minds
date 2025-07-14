# Git Security Guide

A comprehensive guide to securing Git repositories, protecting sensitive data, and implementing security best practices.

## 🛡️ Git Security Fundamentals

### Why Git Security Matters
- **Sensitive Data Exposure**: Credentials, API keys, and secrets can be accidentally committed
- **Access Control**: Unauthorized access to repositories can lead to data breaches
- **Supply Chain Attacks**: Malicious code can be introduced through dependencies
- **Compliance**: Many industries require secure version control practices

### Common Security Threats
- **Credential Leakage**: Hardcoded passwords and API keys
- **Unauthorized Access**: Weak authentication and authorization
- **Malicious Commits**: Code injection and backdoors
- **Repository Cloning**: Unauthorized copying of sensitive code
- **Branch Protection Bypass**: Direct commits to protected branches

## 🔐 Authentication and Authorization

### SSH Key Management

#### Generating SSH Keys
```bash
# Generate Ed25519 key (recommended)
ssh-keygen -t ed25519 -C "your.email@example.com"

# Generate RSA key (if needed)
ssh-keygen -t rsa -b 4096 -C "your.email@example.com"

# Generate key with custom filename
ssh-keygen -t ed25519 -f ~/.ssh/github_key -C "your.email@example.com"
```

#### SSH Key Security
```bash
# Set proper permissions
chmod 600 ~/.ssh/id_ed25519
chmod 644 ~/.ssh/id_ed25519.pub

# Add key to SSH agent
ssh-add ~/.ssh/id_ed25519

# Test SSH connection
ssh -T git@github.com
```

#### SSH Configuration
```bash
# ~/.ssh/config
Host github.com
    HostName github.com
    User git
    IdentityFile ~/.ssh/id_ed25519
    IdentitiesOnly yes
    AddKeysToAgent yes
    UseKeychain yes

Host gitlab.com
    HostName gitlab.com
    User git
    IdentityFile ~/.ssh/id_ed25519
    IdentitiesOnly yes
```

### HTTPS Authentication

#### Personal Access Tokens
```bash
# Configure Git to use token
git config --global credential.helper store
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"

# Use token in URL
git clone https://username:token@github.com/user/repo.git

# Or use credential manager
git config --global credential.helper manager-core
```

#### Token Security Best Practices
- Use fine-grained tokens with minimal permissions
- Set appropriate expiration dates
- Rotate tokens regularly
- Never commit tokens to repositories
- Use environment variables for tokens

### Two-Factor Authentication (2FA)
- Enable 2FA on all Git hosting platforms
- Use authenticator apps instead of SMS
- Keep backup codes in secure location
- Regularly review and update 2FA settings

## 🔒 Repository Security

### Access Control

#### Repository Permissions
```bash
# Check current user
git config user.name
git config user.email

# Set repository-specific user
git config user.name "Project Name"
git config user.email "project@example.com"

# Verify remote URL
git remote -v
```

#### Branch Protection Rules

##### GitHub Branch Protection
```yaml
# .github/branch-protection.yml
branches:
  - name: main
    protection:
      required_status_checks:
        strict: true
        contexts:
          - security-scan
          - code-review
      enforce_admins: true
      required_pull_request_reviews:
        required_approving_review_count: 2
        dismiss_stale_reviews: true
        require_code_owner_reviews: true
        require_last_push_approval: true
      restrictions:
        users: []
        teams: []
      required_linear_history: true
      allow_force_pushes: false
      allow_deletions: false
```

##### GitLab Branch Protection
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
    code_owner_approval_required: true
```

### Code Owners
```bash
# .github/CODEOWNERS
# Global owners
* @admin-team

# Frontend files
*.js @frontend-team
*.css @frontend-team

# Backend files
*.py @backend-team
*.java @backend-team

# Security files
security/ @security-team
*.key @security-team
*.pem @security-team

# Documentation
docs/ @docs-team
*.md @docs-team
```

## 🚨 Preventing Sensitive Data Exposure

### Pre-commit Security Hooks

#### Secret Detection Hook
```bash
#!/bin/sh
# .git/hooks/pre-commit

echo "Running security checks..."

# Check for hardcoded secrets
secrets_patterns=(
    "password.*=.*['\"][^'\"]*['\"]"
    "secret.*=.*['\"][^'\"]*['\"]"
    "api_key.*=.*['\"][^'\"]*['\"]"
    "token.*=.*['\"][^'\"]*['\"]"
    "private_key.*=.*['\"][^'\"]*['\"]"
    "access_key.*=.*['\"][^'\"]*['\"]"
    "secret_key.*=.*['\"][^'\"]*['\"]"
)

for pattern in "${secrets_patterns[@]}"; do
    if git diff --cached | grep -i "$pattern"; then
        echo "Error: Potential hardcoded secret found"
        echo "Pattern: $pattern"
        echo "Please use environment variables instead"
        exit 1
    fi
done

# Check for suspicious file types
suspicious_extensions=("pem" "key" "p12" "pfx" "crt" "cer" "p8" "p7b")

for ext in "${suspicious_extensions[@]}"; do
    if git diff --cached --name-only | grep -q "\.$ext$"; then
        echo "Error: Certificate or key file detected"
        echo "Please do not commit certificate files"
        exit 1
    fi
done

echo "Security checks passed!"
```

#### File Size Check Hook
```bash
#!/bin/sh
# .git/hooks/pre-commit

# Check for large files
max_size=10485760  # 10MB

git diff --cached --name-only | while read file; do
    if [ -f "$file" ]; then
        size=$(stat -f%z "$file" 2>/dev/null || stat -c%s "$file" 2>/dev/null)
        if [ "$size" -gt "$max_size" ]; then
            echo "Error: File $file is too large ($size bytes)"
            echo "Maximum allowed size: $max_size bytes"
            exit 1
        fi
    fi
done
```

### .gitignore Security

#### Comprehensive .gitignore
```bash
# .gitignore

# Environment files
.env
.env.local
.env.development
.env.test
.env.production
.env.*.local

# Credentials and secrets
*.key
*.pem
*.p12
*.pfx
*.crt
*.cer
*.p8
*.p7b
secrets/
credentials/
config/secrets.json
config/credentials.yml

# Logs
*.log
logs/
npm-debug.log*
yarn-debug.log*
yarn-error.log*

# Runtime data
pids
*.pid
*.seed
*.pid.lock

# Coverage directory used by tools like istanbul
coverage/
*.lcov

# Dependency directories
node_modules/
vendor/
.venv/
venv/

# Build outputs
dist/
build/
out/
target/

# IDE files
.vscode/
.idea/
*.swp
*.swo
*~

# OS files
.DS_Store
Thumbs.db

# Temporary files
*.tmp
*.temp
temp/
tmp/
```

### Environment Variables

#### Using Environment Variables
```bash
# .env.example (safe to commit)
DATABASE_URL=postgresql://user:password@localhost/dbname
API_KEY=your_api_key_here
SECRET_KEY=your_secret_key_here

# .env (never commit)
DATABASE_URL=postgresql://real_user:real_password@localhost/real_db
API_KEY=actual_api_key_123456
SECRET_KEY=actual_secret_key_789012
```

#### Environment Variable Validation
```bash
#!/bin/sh
# scripts/validate-env.sh

required_vars=(
    "DATABASE_URL"
    "API_KEY"
    "SECRET_KEY"
)

for var in "${required_vars[@]}"; do
    if [ -z "${!var}" ]; then
        echo "Error: Required environment variable $var is not set"
        exit 1
    fi
done

echo "All required environment variables are set"
```

## 🔍 Security Scanning

### Automated Security Tools

#### GitGuardian Integration
```yaml
# .github/workflows/security-scan.yml
name: Security Scan

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  security-scan:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
      with:
        fetch-depth: 0
    
    - name: GitGuardian scan
      uses: GitGuardian/gg-shield-action@main
      env:
        GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        GITGUARDIAN_API_KEY: ${{ secrets.GITGUARDIAN_API_KEY }}
```

#### TruffleHog Integration
```yaml
# .github/workflows/trufflehog.yml
name: TruffleHog Security Scan

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]

jobs:
  trufflehog:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
      with:
        fetch-depth: 0
    
    - name: Run TruffleHog
      run: |
        docker run --rm -v "$PWD:/pwd" trufflesecurity/trufflehog:latest \
          --only-verified \
          --fail \
          /pwd
```

### Manual Security Checks

#### Repository Audit Script
```bash
#!/bin/bash
# scripts/security-audit.sh

echo "Running Git repository security audit..."

# Check for secrets in history
echo "Checking for secrets in Git history..."
git log --all --full-history --pretty=format: --name-only | \
  xargs -I {} git show {} | \
  grep -i -E "(password|secret|key|token|credential)" | \
  head -20

# Check for large files
echo "Checking for large files..."
git rev-list --objects --all | \
  git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | \
  sed -n 's/^blob //p' | \
  sort -k2nr | \
  head -10

# Check for suspicious file types
echo "Checking for suspicious file types..."
git ls-tree -r HEAD | \
  grep -E "\.(key|pem|p12|pfx|crt|cer|p8|p7b)$"

# Check for environment files
echo "Checking for environment files..."
git ls-tree -r HEAD | \
  grep -E "\.env"

echo "Security audit complete!"
```

## 🛠️ Secure Development Practices

### Commit Message Security

#### Secure Commit Messages
```bash
# Good commit messages
git commit -m "feat: add user authentication"
git commit -m "fix: resolve login security issue"
git commit -m "docs: update security documentation"

# Avoid sensitive information in commit messages
# Bad: git commit -m "fix: update API key to abc123"
# Good: git commit -m "fix: update API configuration"
```

#### Commit Message Validation Hook
```bash
#!/bin/sh
# .git/hooks/commit-msg

# Read commit message
commit_msg=$(cat "$1")

# Check for sensitive patterns
sensitive_patterns=(
    "password.*=.*"
    "secret.*=.*"
    "api_key.*=.*"
    "token.*=.*"
    "key.*=.*"
)

for pattern in "${sensitive_patterns[@]}"; do
    if echo "$commit_msg" | grep -i "$pattern"; then
        echo "Error: Commit message contains sensitive information"
        echo "Pattern: $pattern"
        exit 1
    fi
done
```

### Secure Branching Strategy

#### Protected Branch Workflow
```bash
# Never commit directly to protected branches
# Always use pull requests

# Create feature branch
git checkout -b feature/secure-feature

# Make changes
# ... work on feature ...

# Commit changes
git add .
git commit -m "feat: add secure feature"

# Push and create pull request
git push -u origin feature/secure-feature

# Wait for review and approval
# Merge through pull request interface
```

### Dependency Security

#### Dependency Scanning
```bash
# npm audit
npm audit
npm audit fix

# yarn audit
yarn audit
yarn audit fix

# pip security check
pip-audit

# Ruby bundle audit
bundle audit check
bundle audit update
```

#### Automated Dependency Scanning
```yaml
# .github/workflows/dependency-scan.yml
name: Dependency Security Scan

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 0 * * 0'  # Weekly

jobs:
  dependency-scan:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    
    - name: Setup Node.js
      uses: actions/setup-node@v3
      with:
        node-version: '18'
    
    - name: Install dependencies
      run: npm ci
    
    - name: Run npm audit
      run: npm audit --audit-level=moderate
    
    - name: Run Snyk security scan
      uses: snyk/actions/node@master
      env:
        SNYK_TOKEN: ${{ secrets.SNYK_TOKEN }}
      with:
        args: --severity-threshold=high
```

## 🔄 Incident Response

### Detecting Security Incidents

#### Secret Detection in History
```bash
# Search for secrets in Git history
git log --all --full-history --pretty=format: --name-only | \
  xargs -I {} git show {} | \
  grep -i -E "(password|secret|key|token|credential)" | \
  head -50

# Search for specific patterns
git log -p | grep -i "api_key\|password\|secret"

# Search for files that might contain secrets
git log --all --name-only --pretty=format: | \
  grep -E "\.(key|pem|p12|pfx|crt|cer|p8|p7b|env)$"
```

### Responding to Incidents

#### Immediate Response Steps
```bash
# 1. Revoke exposed credentials immediately
# 2. Remove sensitive data from repository
git filter-branch --force --index-filter \
  'git rm --cached --ignore-unmatch sensitive-file.txt' \
  --prune-empty --tag-name-filter cat -- --all

# 3. Force push to remove from remote
git push origin --force --all
git push origin --force --tags

# 4. Notify team and stakeholders
# 5. Update incident response documentation
```

#### Repository Cleanup
```bash
# Remove sensitive files from history
git filter-branch --force --index-filter \
  'git rm --cached --ignore-unmatch -r sensitive-directory/' \
  --prune-empty --tag-name-filter cat -- --all

# Clean up refs and force garbage collection
git for-each-ref --format="%(refname)" refs/original/ | \
  xargs -n 1 git update-ref -d
git reflog expire --expire=now --all
git gc --prune=now --aggressive
```

### Post-Incident Actions

#### Security Review
```bash
# Conduct thorough security audit
./scripts/security-audit.sh

# Review access controls
git log --oneline --all --author="suspicious-user"

# Check for unauthorized changes
git log --oneline --since="1 week ago" --author="unknown-user"
```

#### Documentation and Training
- Document the incident and response
- Update security procedures
- Provide team training on security best practices
- Review and update security policies

## 📋 Security Checklist

### Repository Setup
- [ ] Enable branch protection rules
- [ ] Configure code owners
- [ ] Set up security scanning
- [ ] Configure access controls
- [ ] Enable 2FA for all users

### Development Practices
- [ ] Use environment variables for secrets
- [ ] Implement pre-commit security hooks
- [ ] Regular dependency scanning
- [ ] Secure commit messages
- [ ] Code review requirements

### Monitoring and Maintenance
- [ ] Regular security audits
- [ ] Monitor for suspicious activity
- [ ] Update security tools
- [ ] Review access permissions
- [ ] Backup security configurations

## 🔗 Security Resources

- [GitHub Security](https://docs.github.com/en/github/managing-security-vulnerabilities)
- [GitLab Security](https://docs.gitlab.com/ee/security/)
- [OWASP Git Security](https://owasp.org/www-project-secure-coding-practices-quick-reference-guide/)
- [GitGuardian](https://www.gitguardian.com/)
- [TruffleHog](https://github.com/trufflesecurity/truffleHog)

---

**Remember**: Security is an ongoing process, not a one-time setup. Regularly review and update your security practices to stay ahead of emerging threats.