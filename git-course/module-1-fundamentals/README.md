# Module 1: Git Fundamentals

## 🎯 Learning Objectives

By the end of this module, you will be able to:
- Understand what version control is and why it's important
- Explain Git's distributed nature and core philosophy
- Install and configure Git on your system
- Create your first Git repository
- Use basic Git commands to track changes
- Understand the three states of Git files

## 📚 Topics Covered

### 1. Understanding Version Control

#### What is Version Control?
Version control is a system that records changes to a file or set of files over time so that you can recall specific versions later. Think of it as a "time machine" for your code.

**Why Version Control?**
- Track changes over time
- Collaborate with others
- Revert to previous versions
- Understand what changed and why
- Backup and recovery

#### Types of Version Control Systems

**Local Version Control**
- Simple database that keeps all changes to files under revision control
- Example: RCS (Revision Control System)

**Centralized Version Control (CVCS)**
- Single server contains all versioned files
- Clients check out files from central place
- Examples: Subversion, Perforce

**Distributed Version Control (DVCS)**
- Clients fully mirror the repository
- Every clone is a full backup
- Examples: Git, Mercurial, Bazaar

### 2. Git Philosophy and Design

#### Git's Core Principles
- **Distributed**: Every clone is a complete repository
- **Fast**: Most operations are local
- **Simple**: Simple design with powerful features
- **Branching**: Cheap and easy branching
- **Data Integrity**: SHA-1 hashing ensures data integrity

#### How Git Works
Git thinks of its data more like a series of snapshots of a miniature filesystem. Every time you commit, Git takes a picture of what all your files look like at that moment and stores a reference to that snapshot.

### 3. Git Installation and Configuration

#### Installing Git

**On Linux:**
```bash
# Ubuntu/Debian
sudo apt-get install git

# CentOS/RHEL/Fedora
sudo yum install git
# or
sudo dnf install git

# Arch Linux
sudo pacman -S git
```

**On macOS:**
```bash
# Using Homebrew
brew install git

# Or download from https://git-scm.com/download/mac
```

**On Windows:**
Download from https://git-scm.com/download/win

#### Initial Configuration

After installation, configure your identity:
```bash
git config --global user.name "Your Name"
git config --global user.email "your.email@example.com"
```

**Other useful configurations:**
```bash
# Set default editor
git config --global core.editor "code --wait"  # VS Code
git config --global core.editor "vim"          # Vim
git config --global core.editor "nano"         # Nano

# Set default branch name
git config --global init.defaultBranch main

# Configure line ending handling
git config --global core.autocrlf input  # Linux/macOS
git config --global core.autocrlf true   # Windows

# View your configuration
git config --list
```

### 4. Your First Git Repository

#### Creating a Repository

**Initialize a new repository:**
```bash
mkdir my-first-project
cd my-first-project
git init
```

**Clone an existing repository:**
```bash
git clone https://github.com/username/repository.git
git clone https://github.com/username/repository.git my-custom-name
```

#### Understanding the Repository Structure

After `git init`, you'll have:
- `.git/` directory (hidden) - contains all Git metadata
- Working directory - where you edit files
- Staging area (index) - where you prepare commits

### 5. Basic Git Commands

#### The Three States

Git has three main states that your files can reside in:
1. **Modified**: File has been changed but not staged
2. **Staged**: File has been marked to be included in next commit
3. **Committed**: File has been safely stored in local database

#### Essential Commands

**Check repository status:**
```bash
git status
```

**Add files to staging area:**
```bash
git add filename.txt          # Add specific file
git add .                     # Add all files
git add *.txt                 # Add all .txt files
git add src/                  # Add all files in src directory
```

**Commit changes:**
```bash
git commit -m "Initial commit"
git commit -m "Add user authentication feature"
```

**View commit history:**
```bash
git log
git log --oneline
git log --graph --oneline --all
```

**View differences:**
```bash
git diff                     # Show unstaged changes
git diff --staged           # Show staged changes
git diff HEAD~1             # Show changes in last commit
```

### 6. Understanding Git Objects

#### The Four Object Types

1. **Blob**: Stores file data
2. **Tree**: Stores directory structure and file names
3. **Commit**: Stores metadata about a commit
4. **Tag**: Stores a reference to a specific commit

#### How Commits Work

When you commit:
1. Git creates a blob for each file
2. Creates a tree representing the directory structure
3. Creates a commit object pointing to the tree
4. Updates the current branch to point to the new commit

## 🛠️ Hands-On Exercises

### Exercise 1: Setting Up Git
1. Install Git on your system
2. Configure your name and email
3. Set your preferred editor
4. Verify installation with `git --version`

### Exercise 2: Your First Repository
1. Create a new directory
2. Initialize a Git repository
3. Create a simple text file
4. Add it to staging
5. Make your first commit

### Exercise 3: Basic Workflow
1. Create multiple files
2. Modify some files
3. Stage specific files
4. Commit changes
5. View the commit history

## 📝 Practice Project: Personal Website

Create a simple personal website and track its development:

1. **Initial Setup**
   ```bash
   mkdir my-website
   cd my-website
   git init
   ```

2. **Create Basic Structure**
   - Create `index.html`
   - Create `styles.css`
   - Create `script.js`

3. **Track Development**
   - Make initial commit with basic structure
   - Add content to HTML
   - Style with CSS
   - Add JavaScript functionality
   - Commit each major change

## ❓ Assessment Questions

1. What is the difference between centralized and distributed version control?
2. Explain the three states of files in Git.
3. What does `git init` do?
4. How do you check the status of your repository?
5. What is the purpose of the staging area?
6. How do you view the commit history?
7. What is the difference between `git add` and `git commit`?

## 🔗 Additional Resources

- [Git Documentation - Getting Started](https://git-scm.com/book/en/v2/Getting-Started-About-Version-Control)
- [GitHub's Git Handbook](https://guides.github.com/introduction/git-handbook/)
- [Git Cheat Sheet](https://education.github.com/git-cheat-sheet-education.pdf)

## 🎯 Next Steps

Once you've completed this module:
- Practice the exercises multiple times
- Complete the practice project
- Take the assessment
- Move on to **Module 2: Core Git Operations**

---

**Remember**: Git is a tool that becomes more powerful with practice. Don't worry if you don't understand everything immediately - the concepts will become clearer as you use Git more.