# Development Environment Setup Guide

## Step 1: Install Visual Studio Code

1. Go to [https://code.visualstudio.com/](https://code.visualstudio.com/)
2. Download the version for your operating system
3. Install and launch VS Code
4. Install recommended extensions:
   - JavaScript (ES6) code snippets
   - TypeScript Importer
   - Prettier - Code formatter
   - ESLint

## Step 2: Install Node.js and npm

### Windows:
1. Go to [https://nodejs.org/](https://nodejs.org/)
2. Download the LTS version
3. Run the installer and follow the prompts
4. Open Command Prompt and verify:
   ```bash
   node --version
   npm --version
   ```

### macOS:
1. Install Homebrew (if not already installed):
   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
   ```
2. Install Node.js:
   ```bash
   brew install node
   ```

### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install nodejs npm
```

## Step 3: Install TypeScript

```bash
npm install -g typescript
```

Verify installation:
```bash
tsc --version
```

## Step 4: Create Your First Project

1. Create a new directory:
   ```bash
   mkdir my-first-project
   cd my-first-project
   ```

2. Initialize a new Node.js project:
   ```bash
   npm init -y
   ```

3. Create your first JavaScript file:
   ```bash
   echo 'console.log("Hello, JavaScript!");' > hello.js
   ```

4. Run it:
   ```bash
   node hello.js
   ```

5. Create your first TypeScript file:
   ```bash
   echo 'console.log("Hello, TypeScript!");' > hello.ts
   ```

6. Compile and run:
   ```bash
   tsc hello.ts
   node hello.js
   ```

## Step 5: Configure TypeScript (Optional)

Create a `tsconfig.json` file:
```bash
tsc --init
```

This creates a basic TypeScript configuration file that you can customize later.

## Step 6: Install Project Dependencies

For a basic project, you might want to install:
```bash
npm install --save-dev typescript @types/node
```

## Verification

Run these commands to verify everything is working:
```bash
node --version
npm --version
tsc --version
```

You should see version numbers for all three tools.

## Next Steps

Now you're ready to start learning! Begin with the JavaScript Fundamentals module.