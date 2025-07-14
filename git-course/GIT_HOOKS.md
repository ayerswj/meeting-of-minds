# Git Hooks Guide

A comprehensive guide to Git hooks, automation, and custom workflows.

## 🎯 What are Git Hooks?

Git hooks are scripts that run automatically before or after Git commands. They allow you to customize Git's behavior and automate tasks in your workflow.

## 📁 Hook Locations

### Local Hooks
- Location: `.git/hooks/`
- Scope: Repository-specific
- Not shared with other developers

### Global Hooks
- Location: `~/.git-templates/hooks/` (configurable)
- Scope: All repositories
- Applied to new repositories

## 🔧 Hook Types

### Client-Side Hooks

#### Pre-commit Hook
**Trigger**: Before commit is created
**Purpose**: Validate code, run tests, check formatting

```bash
#!/bin/sh
# .git/hooks/pre-commit

echo "Running pre-commit checks..."

# Run tests
if [ -f "package.json" ]; then
    echo "Running tests..."
    npm test
    if [ $? -ne 0 ]; then
        echo "Tests failed. Commit aborted."
        exit 1
    fi
fi

# Run linting
if [ -f "package.json" ]; then
    echo "Running linting..."
    npm run lint
    if [ $? -ne 0 ]; then
        echo "Linting failed. Commit aborted."
        exit 1
    fi
fi

# Check for TODO comments
if git diff --cached | grep -i "TODO"; then
    echo "Error: TODO comments found in staged files"
    echo "Please remove TODO comments before committing."
    exit 1
fi

# Check commit message format
commit_msg=$(cat "$1")
if ! echo "$commit_msg" | grep -qE "^(feat|fix|docs|style|refactor|test|chore)(\(.+\))?: .+"; then
    echo "Error: Commit message must follow conventional format"
    echo "Format: type(scope): description"
    echo "Types: feat, fix, docs, style, refactor, test, chore"
    exit 1
fi

echo "Pre-commit checks passed!"
```

#### Prepare-commit-msg Hook
**Trigger**: Before commit message editor is opened
**Purpose**: Modify commit message, add information

```bash
#!/bin/sh
# .git/hooks/prepare-commit-msg

# Add branch name to commit message
branch=$(git branch --show-current)
if [[ $branch != "main" && $branch != "master" ]]; then
    echo "[$branch] " >> "$1"
fi

# Add issue number if branch contains issue reference
issue=$(echo $branch | grep -o '#[0-9]*')
if [ ! -z "$issue" ]; then
    echo "Closes $issue" >> "$1"
fi
```

#### Commit-msg Hook
**Trigger**: After commit message is written
**Purpose**: Validate commit message format

```bash
#!/bin/sh
# .git/hooks/commit-msg

# Read commit message
commit_msg=$(cat "$1")

# Check conventional commit format
if ! echo "$commit_msg" | grep -qE "^(feat|fix|docs|style|refactor|test|chore)(\(.+\))?: .+"; then
    echo "Error: Invalid commit message format"
    echo "Expected: type(scope): description"
    echo "Types: feat, fix, docs, style, refactor, test, chore"
    exit 1
fi

# Check message length
if [ ${#commit_msg} -gt 72 ]; then
    echo "Error: Commit message too long (max 72 characters)"
    exit 1
fi
```

#### Post-commit Hook
**Trigger**: After commit is created
**Purpose**: Notifications, logging, deployment triggers

```bash
#!/bin/sh
# .git/hooks/post-commit

# Get commit information
commit_hash=$(git rev-parse HEAD)
commit_msg=$(git log -1 --pretty=format:%s)
author=$(git log -1 --pretty=format:%an)
branch=$(git branch --show-current)

echo "Commit created: $commit_hash"
echo "Message: $commit_msg"
echo "Author: $author"
echo "Branch: $branch"

# Send notification (example with curl)
# curl -X POST https://api.example.com/notifications \
#   -H "Content-Type: application/json" \
#   -d "{\"commit\":\"$commit_hash\",\"message\":\"$commit_msg\",\"author\":\"$author\"}"

# Log to file
echo "$(date): $commit_hash - $commit_msg by $author on $branch" >> .git/commit-log.txt
```

#### Pre-push Hook
**Trigger**: Before push to remote
**Purpose**: Run full test suite, security checks

```bash
#!/bin/sh
# .git/hooks/pre-push

echo "Running pre-push checks..."

# Run full test suite
if [ -f "package.json" ]; then
    echo "Running full test suite..."
    npm run test:full
    if [ $? -ne 0 ]; then
        echo "Tests failed. Push aborted."
        exit 1
    fi
fi

# Run security audit
if [ -f "package.json" ]; then
    echo "Running security audit..."
    npm audit
    if [ $? -ne 0 ]; then
        echo "Security vulnerabilities found. Push aborted."
        exit 1
    fi
fi

# Check for sensitive data
if git diff --cached | grep -i "password\|secret\|key\|token"; then
    echo "Warning: Potential sensitive data detected"
    read -p "Continue anyway? (y/N) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi

echo "Pre-push checks passed!"
```

### Server-Side Hooks

#### Pre-receive Hook
**Trigger**: Before push is accepted on server
**Purpose**: Validate incoming changes

```bash
#!/bin/sh
# .git/hooks/pre-receive

echo "Validating incoming changes..."

# Check for large files
while read oldrev newrev refname; do
    if [ "$oldrev" = "0000000000000000000000000000000000000000" ]; then
        # New branch
        git diff-tree --no-commit-id --name-only -r $newrev | while read file; do
            size=$(git show $newrev:$file | wc -c)
            if [ $size -gt 10485760 ]; then  # 10MB limit
                echo "Error: File $file is too large ($size bytes)"
                exit 1
            fi
        done
    else
        # Existing branch
        git diff-tree --no-commit-id --name-only -r $oldrev $newrev | while read file; do
            size=$(git show $newrev:$file | wc -c)
            if [ $size -gt 10485760 ]; then  # 10MB limit
                echo "Error: File $file is too large ($size bytes)"
                exit 1
            fi
        done
    fi
done
```

#### Update Hook
**Trigger**: Before each ref is updated
**Purpose**: Validate specific branch updates

```bash
#!/bin/sh
# .git/hooks/update

refname="$1"
oldrev="$2"
newrev="$3"

echo "Updating $refname from $oldrev to $newrev"

# Protect main branch
if [ "$refname" = "refs/heads/main" ]; then
    # Only allow fast-forward merges
    if ! git merge-base --is-ancestor $oldrev $newrev; then
        echo "Error: Non-fast-forward merge to main branch not allowed"
        exit 1
    fi
fi

# Check commit message format
git rev-list $oldrev..$newrev | while read commit; do
    msg=$(git log -1 --pretty=format:%s $commit)
    if ! echo "$msg" | grep -qE "^(feat|fix|docs|style|refactor|test|chore)(\(.+\))?: .+"; then
        echo "Error: Invalid commit message format in $commit"
        echo "Message: $msg"
        exit 1
    fi
done
```

#### Post-receive Hook
**Trigger**: After push is accepted on server
**Purpose**: Deployment, notifications, logging

```bash
#!/bin/sh
# .git/hooks/post-receive

echo "Processing received changes..."

while read oldrev newrev refname; do
    branch=$(echo $refname | sed 's|refs/heads/||')
    
    echo "Branch $branch updated from $oldrev to $newrev"
    
    # Deploy to staging if develop branch
    if [ "$branch" = "develop" ]; then
        echo "Deploying to staging..."
        # Add your deployment script here
        # ./deploy-staging.sh
    fi
    
    # Deploy to production if main branch
    if [ "$branch" = "main" ]; then
        echo "Deploying to production..."
        # Add your deployment script here
        # ./deploy-production.sh
    fi
    
    # Send notifications
    curl -X POST https://api.example.com/deploy/webhook \
      -H "Content-Type: application/json" \
      -d "{\"branch\":\"$branch\",\"oldrev\":\"$oldrev\",\"newrev\":\"$newrev\"}"
done
```

## 🛠️ Advanced Hook Examples

### Automated Testing Hook
```bash
#!/bin/sh
# .git/hooks/pre-commit

# Run different tests based on file changes
changed_files=$(git diff --cached --name-only)

# Run JavaScript tests if JS files changed
if echo "$changed_files" | grep -q "\.js$"; then
    echo "Running JavaScript tests..."
    npm test
    if [ $? -ne 0 ]; then
        echo "JavaScript tests failed"
        exit 1
    fi
fi

# Run Python tests if Python files changed
if echo "$changed_files" | grep -q "\.py$"; then
    echo "Running Python tests..."
    python -m pytest
    if [ $? -ne 0 ]; then
        echo "Python tests failed"
        exit 1
    fi
fi

# Run database migrations if SQL files changed
if echo "$changed_files" | grep -q "\.sql$"; then
    echo "Running database migration tests..."
    # Add your migration test script here
fi
```

### Code Quality Hook
```bash
#!/bin/sh
# .git/hooks/pre-commit

# Run ESLint for JavaScript files
if git diff --cached --name-only | grep -q "\.js$"; then
    echo "Running ESLint..."
    npx eslint --fix $(git diff --cached --name-only | grep "\.js$")
    if [ $? -ne 0 ]; then
        echo "ESLint found issues. Please fix them."
        exit 1
    fi
fi

# Run Prettier for formatting
if git diff --cached --name-only | grep -q "\.(js|jsx|ts|tsx|css|scss|json)$"; then
    echo "Running Prettier..."
    npx prettier --write $(git diff --cached --name-only | grep "\.(js|jsx|ts|tsx|css|scss|json)$")
    git add $(git diff --cached --name-only | grep "\.(js|jsx|ts|tsx|css|scss|json)$")
fi

# Check for console.log statements
if git diff --cached | grep -q "console\.log"; then
    echo "Warning: console.log statements found"
    echo "Consider removing them before committing"
    read -p "Continue anyway? (y/N) " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
fi
```

### Security Hook
```bash
#!/bin/sh
# .git/hooks/pre-commit

# Check for hardcoded secrets
secrets_patterns=(
    "password.*=.*['\"][^'\"]*['\"]"
    "secret.*=.*['\"][^'\"]*['\"]"
    "api_key.*=.*['\"][^'\"]*['\"]"
    "token.*=.*['\"][^'\"]*['\"]"
    "private_key.*=.*['\"][^'\"]*['\"]"
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
suspicious_extensions=("pem" "key" "p12" "pfx" "crt" "cer")

for ext in "${suspicious_extensions[@]}"; do
    if git diff --cached --name-only | grep -q "\.$ext$"; then
        echo "Error: Certificate or key file detected"
        echo "Please do not commit certificate files"
        exit 1
    fi
done
```

## 🔧 Hook Management

### Installing Hooks
```bash
# Make hooks executable
chmod +x .git/hooks/*

# Create symbolic link to shared hooks
ln -s ../../hooks/pre-commit .git/hooks/pre-commit
```

### Using Husky (Node.js)
```bash
# Install Husky
npm install --save-dev husky

# Initialize Husky
npx husky install

# Add pre-commit hook
npx husky add .husky/pre-commit "npm test"

# Add commit-msg hook
npx husky add .husky/commit-msg "npx --no -- commitlint --edit \$1"
```

### Using Pre-commit (Python)
```bash
# Install pre-commit
pip install pre-commit

# Create .pre-commit-config.yaml
repos:
  - repo: https://github.com/pre-commit/pre-commit-hooks
    rev: v4.4.0
    hooks:
      - id: trailing-whitespace
      - id: end-of-file-fixer
      - id: check-yaml
      - id: check-added-large-files

# Install hooks
pre-commit install
```

## 📊 Hook Best Practices

### Performance
- Keep hooks fast (under 10 seconds)
- Use caching for expensive operations
- Run only necessary checks based on file changes

### Reliability
- Always provide clear error messages
- Include instructions for fixing issues
- Test hooks thoroughly before deployment

### Maintainability
- Use version control for hooks
- Document hook behavior
- Keep hooks simple and focused

### Security
- Validate all inputs
- Don't trust user data
- Use secure communication for notifications

## 🚀 Advanced Hook Patterns

### Conditional Execution
```bash
#!/bin/sh
# Only run on specific branches
branch=$(git branch --show-current)
if [ "$branch" = "main" ] || [ "$branch" = "develop" ]; then
    # Run expensive checks only on important branches
    npm run test:full
fi
```

### Parallel Execution
```bash
#!/bin/sh
# Run multiple checks in parallel
npm test &
npm run lint &
wait

# Check exit codes
if [ $? -ne 0 ]; then
    echo "Some checks failed"
    exit 1
fi
```

### Hook Chaining
```bash
#!/bin/sh
# Chain multiple hooks
./hooks/check-format.sh
./hooks/run-tests.sh
./hooks/security-scan.sh
```

## 📚 Resources

- [Git Hooks Documentation](https://git-scm.com/docs/githooks)
- [Husky Documentation](https://typicode.github.io/husky/)
- [Pre-commit Documentation](https://pre-commit.com/)
- [Git Hooks Examples](https://github.com/git-hooks/git-hooks)

---

**Remember**: Hooks are powerful tools for automation, but they should enhance your workflow, not hinder it. Start simple and add complexity as needed.