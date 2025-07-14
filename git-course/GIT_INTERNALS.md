# Git Internals Guide

A deep dive into Git's internal structure, object model, and how Git works under the hood.

## 🎯 Understanding Git's Design Philosophy

Git was designed with several key principles in mind:
- **Distributed**: Every clone is a complete repository
- **Fast**: Most operations are local
- **Simple**: Simple design with powerful features
- **Branching**: Cheap and easy branching
- **Data Integrity**: SHA-1 hashing ensures data integrity

## 📁 Git Repository Structure

### .git Directory Contents
```
.git/
├── HEAD                    # Points to current branch
├── config                  # Repository configuration
├── description             # Repository description
├── hooks/                  # Git hooks
├── info/                   # Repository information
├── objects/                # Git objects (blobs, trees, commits, tags)
│   ├── info/              # Object information
│   └── pack/              # Packed objects
├── refs/                   # References (branches, tags)
│   ├── heads/             # Branch references
│   ├── tags/              # Tag references
│   └── remotes/           # Remote references
└── logs/                   # Reference logs
    ├── HEAD               # HEAD reflog
    └── refs/              # Branch reflogs
```

### Key Files and Directories

#### HEAD
Points to the current branch or commit.
```bash
# View HEAD content
cat .git/HEAD
# Output: ref: refs/heads/main

# View what HEAD points to
git rev-parse HEAD
# Output: abc1234...
```

#### Config
Repository-specific configuration.
```bash
# View config
cat .git/config

# Example config content:
[core]
    repositoryformatversion = 0
    filemode = true
    bare = false
    logallrefupdates = true
[remote "origin"]
    url = https://github.com/user/repo.git
    fetch = +refs/heads/*:refs/remotes/origin/*
[branch "main"]
    remote = origin
    merge = refs/heads/main
```

## 🔍 Git Object Model

Git stores everything as objects in the `.git/objects` directory. There are four types of objects:

### 1. Blob Objects
Store file content.

#### Creating a Blob
```bash
# Create blob from file
git hash-object -w filename.txt
# Output: a1b2c3d4e5f6...

# Create blob from stdin
echo "Hello, World!" | git hash-object -w --stdin
# Output: 8ab686eafeb1f44702738c8b0f24f2567c36da6d
```

#### Examining a Blob
```bash
# Show blob content
git cat-file -p a1b2c3d4e5f6

# Show blob type
git cat-file -t a1b2c3d4e5f6
# Output: blob

# Show blob size
git cat-file -s a1b2c3d4e5f6
# Output: 13
```

### 2. Tree Objects
Store directory structure and file metadata.

#### Creating a Tree
```bash
# Add files to staging area
git add file1.txt file2.txt

# Create tree from index
git write-tree
# Output: 1234567890abcdef...

# Show tree structure
git ls-tree 1234567890abcdef
# Output:
# 100644 blob a1b2c3d4e5f6 file1.txt
# 100644 blob f6e5d4c3b2a1 file2.txt
```

#### Examining a Tree
```bash
# Show tree content
git cat-file -p 1234567890abcdef

# Show tree recursively
git ls-tree -r 1234567890abcdef

# Show tree type
git cat-file -t 1234567890abcdef
# Output: tree
```

### 3. Commit Objects
Store metadata about a commit.

#### Creating a Commit
```bash
# Create commit from tree
git commit-tree 1234567890abcdef -m "Initial commit"
# Output: abcdef1234567890...

# Create commit with parent
git commit-tree 1234567890abcdef -p parent_commit -m "Second commit"
```

#### Examining a Commit
```bash
# Show commit content
git cat-file -p abcdef1234567890

# Output:
# tree 1234567890abcdef
# author John Doe <john@example.com> 1234567890 +0000
# committer John Doe <john@example.com> 1234567890 +0000
# 
# Initial commit

# Show commit type
git cat-file -t abcdef1234567890
# Output: commit
```

### 4. Tag Objects
Store references to specific commits.

#### Creating a Tag
```bash
# Create annotated tag
git tag -a v1.0.0 -m "Release v1.0.0"

# Show tag object
git cat-file -p v1.0.0

# Output:
# object abcdef1234567890
# type commit
# tag v1.0.0
# tagger John Doe <john@example.com> 1234567890 +0000
# 
# Release v1.0.0
```

## 🔗 Object Relationships

### How Objects Connect
```
Commit Object
├── Points to Tree Object
│   ├── Points to Blob Objects (files)
│   └── Points to Tree Objects (subdirectories)
└── Points to Parent Commit Objects
```

### Example Object Graph
```bash
# Create a simple repository
mkdir git-internals-demo
cd git-internals-demo
git init

# Create files
echo "Hello, World!" > hello.txt
echo "Git is awesome!" > git.txt

# Add files
git add hello.txt git.txt

# Create tree
tree_hash=$(git write-tree)
echo "Tree hash: $tree_hash"

# Create commit
commit_hash=$(git commit-tree $tree_hash -m "Initial commit")
echo "Commit hash: $commit_hash"

# Update HEAD
git update-ref HEAD $commit_hash

# Show object relationships
echo "Commit object:"
git cat-file -p $commit_hash

echo "Tree object:"
git cat-file -p $tree_hash

echo "Blob objects:"
git ls-tree $tree_hash | while read mode type hash filename; do
    echo "File: $filename"
    echo "Content: $(git cat-file -p $hash)"
    echo "---"
done
```

## 📍 References and Refspecs

### Reference Types

#### Branch References
```bash
# Branch reference format
refs/heads/branch-name

# View branch reference
cat .git/refs/heads/main
# Output: abcdef1234567890...

# Update branch reference
git update-ref refs/heads/main abcdef1234567890
```

#### Tag References
```bash
# Tag reference format
refs/tags/tag-name

# View tag reference
cat .git/refs/tags/v1.0.0
# Output: abcdef1234567890...
```

#### Remote References
```bash
# Remote reference format
refs/remotes/remote-name/branch-name

# View remote reference
cat .git/refs/remotes/origin/main
# Output: abcdef1234567890...
```

### Refspecs
Refspecs define mappings between local and remote references.

#### Refspec Format
```
<source>:<destination>
```

#### Common Refspecs
```bash
# Push local main to remote main
git push origin main:main

# Push local feature to remote feature
git push origin feature:feature

# Fetch remote develop to local develop
git fetch origin develop:develop

# Delete remote branch
git push origin :feature-branch

# Push all local branches
git push origin refs/heads/*:refs/heads/*

# Fetch all remote branches
git fetch origin refs/heads/*:refs/remotes/origin/*
```

## 🔄 Git Index (Staging Area)

### Index Structure
The index is a binary file that stores information about staged files.

#### Viewing Index
```bash
# Show index contents
git ls-files --stage

# Output:
# 100644 a1b2c3d4e5f6 0 file1.txt
# 100644 f6e5d4c3b2a1 0 file2.txt

# Show index in detail
git ls-files --stage --debug
```

#### Index Operations
```bash
# Add file to index
git update-index --add filename.txt

# Remove file from index
git update-index --remove filename.txt

# Update file in index
git update-index filename.txt

# Clear index
git read-tree --empty
```

### Index vs Working Directory
```bash
# Show differences between index and working directory
git diff

# Show differences between HEAD and index
git diff --cached

# Show differences between HEAD and working directory
git diff HEAD
```

## 🗂️ Pack Files and Compression

### Object Storage
Git stores objects in two formats:
- **Loose objects**: Individual files in `.git/objects/`
- **Pack files**: Compressed collections in `.git/objects/pack/`

### Pack File Operations
```bash
# Create pack file
git gc

# Aggressive packing
git gc --aggressive

# Repack with specific options
git repack -a -d --depth=250 --window=250

# Show pack file information
git verify-pack -v .git/objects/pack/*.idx
```

### Delta Compression
Git uses delta compression to store similar objects efficiently.

```bash
# Show delta chains
git verify-pack -v .git/objects/pack/*.idx | grep -E "(delta|chain)"

# Analyze pack file
git show-index .git/objects/pack/*.idx
```

## 🔍 Plumbing Commands

### Object Manipulation
```bash
# Hash object
git hash-object filename.txt
git hash-object -w filename.txt  # Write to database

# Show object
git cat-file -p <object>
git cat-file -t <object>  # Type
git cat-file -s <object>  # Size

# List tree
git ls-tree <tree>
git ls-tree -r <tree>  # Recursive
git ls-tree -t <tree>  # Tree objects only

# Write tree
git write-tree

# Create commit
git commit-tree <tree> -p <parent> -m "message"
```

### Reference Manipulation
```bash
# Update reference
git update-ref refs/heads/branch-name <commit>

# Delete reference
git update-ref -d refs/heads/branch-name

# Show reference
git show-ref

# List references
git for-each-ref --format='%(refname) %(objectname)'
```

### Index Manipulation
```bash
# Update index
git update-index --add filename.txt
git update-index --remove filename.txt

# Read tree into index
git read-tree <tree>

# Write index to tree
git write-tree

# Show index
git ls-files --stage
```

## 🔧 Advanced Internal Operations

### Manual Object Creation
```bash
# Create blob manually
echo "Hello, World!" | git hash-object -w --stdin

# Create tree manually
git update-index --add filename.txt
git write-tree

# Create commit manually
git commit-tree <tree> -p <parent> -m "message"

# Update reference manually
git update-ref HEAD <commit>
```

### Object Inspection
```bash
# Find object type
git cat-file -t <object>

# Show object content
git cat-file -p <object>

# Show object size
git cat-file -s <object>

# Show object in binary
git cat-file -w <object>
```

### Reference Inspection
```bash
# Show all references
git show-ref

# Show specific reference
git show-ref refs/heads/main

# Show reference log
git reflog show HEAD

# Show reference history
git log --oneline --decorate --all
```

## 🛠️ Internal Debugging

### Repository Integrity
```bash
# Check repository integrity
git fsck --full

# Find unreachable objects
git fsck --lost-found

# Check object connectivity
git fsck --connectivity-only

# Verify pack files
git verify-pack -v .git/objects/pack/*.idx
```

### Performance Analysis
```bash
# Count objects
git count-objects -v

# Show repository size
du -sh .git

# Find large files
git rev-list --objects --all | git cat-file --batch-check='%(objecttype) %(objectname) %(objectsize) %(rest)' | sed -n 's/^blob //p' | sort -k2nr | head -10

# Analyze pack files
git verify-pack -v .git/objects/pack/*.idx | sort -k 3 -n | tail -10
```

### Object Statistics
```bash
# Show object statistics
git count-objects -v

# Show pack statistics
git verify-pack -v .git/objects/pack/*.idx | grep -E "(pack|objects)"

# Show reference statistics
git for-each-ref --format='%(refname) %(objectname)' | wc -l
```

## 🔍 Understanding Git's Data Model

### Content-Addressable Storage
Git uses content-addressable storage, meaning objects are identified by their content hash.

```bash
# Same content = same hash
echo "Hello, World!" | git hash-object -w --stdin
echo "Hello, World!" | git hash-object -w --stdin
# Both produce the same hash

# Different content = different hash
echo "Hello, World!" | git hash-object -w --stdin
echo "Hello, Git!" | git hash-object -w --stdin
# Different hashes
```

### Immutable Objects
Once created, Git objects are immutable. Changes create new objects.

```bash
# Create initial file
echo "Hello" > file.txt
git add file.txt
git commit -m "Initial"

# Modify file
echo "Hello, World!" > file.txt
git add file.txt
git commit -m "Modified"

# Show different blobs
git log --oneline --follow file.txt
git show HEAD:file.txt
git show HEAD~1:file.txt
```

### Object Relationships
Objects form a directed acyclic graph (DAG).

```bash
# Show commit graph
git log --graph --oneline --all

# Show object relationships
git show --name-only <commit>

# Show tree structure
git ls-tree -r <tree>
```

## 📚 Advanced Topics

### Custom Git Objects
```bash
# Create custom blob
echo "Custom content" | git hash-object -w --stdin

# Create custom tree
git update-index --add --cacheinfo 100644 <blob-hash> filename.txt
git write-tree

# Create custom commit
git commit-tree <tree-hash> -m "Custom commit"
```

### Git Protocol
```bash
# Clone using git protocol
git clone git://github.com/user/repo.git

# Push using git protocol
git push git://github.com/user/repo.git main

# Fetch using git protocol
git fetch git://github.com/user/repo.git
```

### Git Transfer Protocols
- **Local**: Direct file system access
- **SSH**: Secure shell protocol
- **HTTP/HTTPS**: Web protocols
- **Git**: Custom Git protocol

## 🔗 Resources

- [Git Internals Documentation](https://git-scm.com/book/en/v2/Git-Internals-Plumbing-and-Porcelain)
- [Git Objects](https://git-scm.com/book/en/v2/Git-Internals-Git-Objects)
- [Git References](https://git-scm.com/book/en/v2/Git-Internals-Git-References)
- [Git Packfiles](https://git-scm.com/book/en/v2/Git-Internals-Packfiles)

---

**Remember**: Understanding Git's internals helps you use Git more effectively and troubleshoot complex issues. The object model is the foundation of everything Git does.