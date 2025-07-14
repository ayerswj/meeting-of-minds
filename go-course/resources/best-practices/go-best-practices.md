# Go Best Practices Guide

## 🎯 Overview

This guide covers essential best practices for writing clean, maintainable, and efficient Go code. Following these practices will help you write idiomatic Go code and avoid common pitfalls.

---

## 📝 Code Organization

### Package Structure
```
project/
├── cmd/
│   └── main.go
├── internal/
│   ├── handlers/
│   ├── models/
│   └── services/
├── pkg/
│   └── utils/
├── api/
├── docs/
├── tests/
├── go.mod
└── README.md
```

### Package Naming
- Use short, concise names
- Avoid underscores and mixed caps
- Use singular form for package names

```go
// Good
package user
package auth
package config

// Avoid
package user_management
package UserAuth
package configurations
```

### File Organization
- One package per directory
- Related functionality in the same package
- Separate interfaces from implementations
- Keep files under 500 lines when possible

---

## 🔧 Naming Conventions

### Variables and Functions
```go
// Use camelCase for variables and functions
var userName string
var isActive bool
var maxRetries int

func getUserByID(id int) (*User, error) {
    // implementation
}

// Use PascalCase for exported names
type User struct {
    Name string
    Age  int
}

func NewUser(name string, age int) *User {
    return &User{Name: name, Age: age}
}
```

### Constants
```go
// Use UPPER_CASE for constants
const (
    MaxRetries = 3
    DefaultTimeout = 30 * time.Second
    StatusOK = 200
)

// Use camelCase for package-level constants
const defaultPort = 8080
```

### Interfaces
```go
// Use descriptive names ending with -er when appropriate
type Reader interface {
    Read(p []byte) (n int, err error)
}

type Writer interface {
    Write(p []byte) (n int, err error)
}

// For single-method interfaces, use the method name + -er
type Stringer interface {
    String() string
}
```

---

## 🏗️ Error Handling

### Always Check Errors
```go
// Good
file, err := os.Open("file.txt")
if err != nil {
    return fmt.Errorf("failed to open file: %w", err)
}
defer file.Close()

// Avoid
file, _ := os.Open("file.txt") // Never ignore errors
```

### Custom Error Types
```go
type ValidationError struct {
    Field   string
    Message string
}

func (e ValidationError) Error() string {
    return fmt.Sprintf("validation error on %s: %s", e.Field, e.Message)
}

// Usage
if age < 0 {
    return ValidationError{Field: "age", Message: "must be positive"}
}
```

### Error Wrapping (Go 1.13+)
```go
// Use fmt.Errorf with %w verb
if err != nil {
    return fmt.Errorf("failed to process user %d: %w", userID, err)
}

// Unwrap errors
var validationErr ValidationError
if errors.As(err, &validationErr) {
    // Handle validation error
}
```

---

## 🔄 Concurrency

### Goroutines
```go
// Always handle goroutine lifecycle
func processItems(items []Item) {
    var wg sync.WaitGroup
    results := make(chan Result, len(items))
    
    for _, item := range items {
        wg.Add(1)
        go func(item Item) {
            defer wg.Done()
            result := processItem(item)
            results <- result
        }(item)
    }
    
    go func() {
        wg.Wait()
        close(results)
    }()
    
    for result := range results {
        // Handle result
    }
}
```

### Channels
```go
// Use buffered channels when appropriate
ch := make(chan int, 100) // Buffered for performance

// Close channels from sender, not receiver
func producer(ch chan<- int) {
    defer close(ch)
    for i := 0; i < 10; i++ {
        ch <- i
    }
}

// Use select for non-blocking operations
select {
case msg := <-ch:
    fmt.Println("Received:", msg)
case <-time.After(time.Second):
    fmt.Println("Timeout")
default:
    fmt.Println("No message ready")
}
```

### Context Usage
```go
// Always pass context as first parameter
func fetchData(ctx context.Context, url string) ([]byte, error) {
    req, err := http.NewRequestWithContext(ctx, "GET", url, nil)
    if err != nil {
        return nil, err
    }
    
    resp, err := http.DefaultClient.Do(req)
    if err != nil {
        return nil, err
    }
    defer resp.Body.Close()
    
    return io.ReadAll(resp.Body)
}
```

---

## 🧪 Testing

### Test Structure
```go
// Use table-driven tests
func TestAdd(t *testing.T) {
    tests := []struct {
        name     string
        a, b     int
        expected int
    }{
        {"positive numbers", 1, 2, 3},
        {"negative numbers", -1, -2, -3},
        {"zero", 0, 5, 5},
    }
    
    for _, tt := range tests {
        t.Run(tt.name, func(t *testing.T) {
            result := add(tt.a, tt.b)
            if result != tt.expected {
                t.Errorf("add(%d, %d) = %d; want %d", 
                    tt.a, tt.b, result, tt.expected)
            }
        })
    }
}
```

### Benchmarking
```go
func BenchmarkProcessItems(b *testing.B) {
    items := generateTestItems(1000)
    
    b.ResetTimer()
    for i := 0; i < b.N; i++ {
        processItems(items)
    }
}
```

### Test Coverage
```bash
# Run tests with coverage
go test -cover ./...

# Generate coverage report
go test -coverprofile=coverage.out ./...
go tool cover -html=coverage.out
```

---

## 📦 Package Management

### Go Modules
```go
// go.mod
module myproject

go 1.21

require (
    github.com/gorilla/mux v1.8.0
    github.com/lib/pq v1.10.9
)

// Use go mod tidy to clean up dependencies
// Use go mod vendor for vendoring
```

### Dependency Management
```go
// Pin specific versions for stability
go get github.com/gorilla/mux@v1.8.0

// Use go mod tidy to clean up unused dependencies
go mod tidy

// Check for security vulnerabilities
go list -json -deps ./... | nancy sleuth
```

---

## 🔒 Security

### Input Validation
```go
// Always validate input
func createUser(name, email string) (*User, error) {
    if name == "" {
        return nil, errors.New("name is required")
    }
    
    if !isValidEmail(email) {
        return nil, errors.New("invalid email format")
    }
    
    return &User{Name: name, Email: email}, nil
}
```

### SQL Injection Prevention
```go
// Use parameterized queries
func getUserByID(db *sql.DB, id int) (*User, error) {
    query := "SELECT id, name, email FROM users WHERE id = ?"
    row := db.QueryRow(query, id)
    
    var user User
    err := row.Scan(&user.ID, &user.Name, &user.Email)
    if err != nil {
        return nil, err
    }
    
    return &user, nil
}
```

### Environment Variables
```go
// Use environment variables for configuration
type Config struct {
    Port     string
    Database string
    APIKey   string
}

func loadConfig() (*Config, error) {
    port := os.Getenv("PORT")
    if port == "" {
        port = "8080"
    }
    
    database := os.Getenv("DATABASE_URL")
    if database == "" {
        return nil, errors.New("DATABASE_URL is required")
    }
    
    return &Config{
        Port:     port,
        Database: database,
        APIKey:   os.Getenv("API_KEY"),
    }, nil
}
```

---

## 🚀 Performance

### Memory Management
```go
// Use sync.Pool for frequently allocated objects
var userPool = sync.Pool{
    New: func() interface{} {
        return &User{}
    },
}

func processUser() {
    user := userPool.Get().(*User)
    defer userPool.Put(user)
    
    // Use user
    user.Name = "John"
    user.Age = 30
}
```

### String Concatenation
```go
// Use strings.Builder for multiple concatenations
func buildMessage(parts []string) string {
    var builder strings.Builder
    for _, part := range parts {
        builder.WriteString(part)
        builder.WriteString(" ")
    }
    return strings.TrimSpace(builder.String())
}
```

### Slices and Maps
```go
// Pre-allocate slices when size is known
func processItems(count int) []Item {
    items := make([]Item, 0, count) // Pre-allocate capacity
    for i := 0; i < count; i++ {
        items = append(items, Item{ID: i})
    }
    return items
}

// Use map for lookups
func findUserByID(users []User, id int) *User {
    userMap := make(map[int]*User, len(users))
    for i := range users {
        userMap[users[i].ID] = &users[i]
    }
    return userMap[id]
}
```

---

## 📚 Documentation

### Package Documentation
```go
// Package user provides user management functionality.
//
// This package includes user creation, validation, and storage operations.
// It supports both local and remote user databases.
package user

// User represents a user in the system.
type User struct {
    ID    int    `json:"id"`
    Name  string `json:"name"`
    Email string `json:"email"`
}

// NewUser creates a new user with the given name and email.
// It returns an error if the email format is invalid.
func NewUser(name, email string) (*User, error) {
    // implementation
}
```

### Function Documentation
```go
// ProcessItems processes a slice of items concurrently.
// It returns a channel that will receive the results as they complete.
// The function respects the provided context for cancellation.
func ProcessItems(ctx context.Context, items []Item) <-chan Result {
    // implementation
}
```

---

## 🔧 Configuration

### Configuration Structs
```go
type Config struct {
    Server   ServerConfig   `yaml:"server"`
    Database DatabaseConfig `yaml:"database"`
    Logging  LoggingConfig  `yaml:"logging"`
}

type ServerConfig struct {
    Port    int    `yaml:"port"`
    Host    string `yaml:"host"`
    Timeout time.Duration `yaml:"timeout"`
}

func LoadConfig(path string) (*Config, error) {
    data, err := os.ReadFile(path)
    if err != nil {
        return nil, fmt.Errorf("failed to read config file: %w", err)
    }
    
    var config Config
    if err := yaml.Unmarshal(data, &config); err != nil {
        return nil, fmt.Errorf("failed to parse config: %w", err)
    }
    
    return &config, nil
}
```

---

## 🐛 Debugging and Logging

### Structured Logging
```go
import "log/slog"

func main() {
    logger := slog.New(slog.NewJSONHandler(os.Stdout, nil))
    
    logger.Info("server started",
        "port", 8080,
        "environment", "production",
        "version", "1.0.0",
    )
    
    logger.Error("failed to process request",
        "error", err,
        "user_id", userID,
        "request_id", requestID,
    )
}
```

### Debug Information
```go
// Use build tags for debug code
// +build debug

package main

func debugPrint(v interface{}) {
    if debug {
        fmt.Printf("DEBUG: %+v\n", v)
    }
}
```

---

## 🔄 API Design

### RESTful APIs
```go
// Use standard HTTP methods
func (h *UserHandler) ServeHTTP(w http.ResponseWriter, r *http.Request) {
    switch r.Method {
    case "GET":
        h.getUser(w, r)
    case "POST":
        h.createUser(w, r)
    case "PUT":
        h.updateUser(w, r)
    case "DELETE":
        h.deleteUser(w, r)
    default:
        http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
    }
}
```

### Response Format
```go
type APIResponse struct {
    Success bool        `json:"success"`
    Message string      `json:"message"`
    Data    interface{} `json:"data,omitempty"`
    Error   string      `json:"error,omitempty"`
}

func (h *UserHandler) getUser(w http.ResponseWriter, r *http.Request) {
    user, err := h.service.GetUser(r.Context(), userID)
    if err != nil {
        response := APIResponse{
            Success: false,
            Error:   err.Error(),
        }
        w.WriteHeader(http.StatusInternalServerError)
        json.NewEncoder(w).Encode(response)
        return
    }
    
    response := APIResponse{
        Success: true,
        Message: "User retrieved successfully",
        Data:    user,
    }
    
    w.Header().Set("Content-Type", "application/json")
    json.NewEncoder(w).Encode(response)
}
```

---

## 🚀 Deployment

### Docker
```dockerfile
# Multi-stage build
FROM golang:1.21-alpine AS builder
WORKDIR /app
COPY go.mod go.sum ./
RUN go mod download
COPY . .
RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o main .

FROM alpine:latest
RUN apk --no-cache add ca-certificates
WORKDIR /root/
COPY --from=builder /app/main .
CMD ["./main"]
```

### Environment Configuration
```go
// Use environment-specific configuration
func getConfig() *Config {
    env := os.Getenv("ENVIRONMENT")
    switch env {
    case "production":
        return loadProductionConfig()
    case "staging":
        return loadStagingConfig()
    default:
        return loadDevelopmentConfig()
    }
}
```

---

## 📋 Code Review Checklist

- [ ] Code follows Go formatting standards (`gofmt`)
- [ ] All errors are properly handled
- [ ] Functions are small and focused
- [ ] Variable names are descriptive
- [ ] Comments explain "why" not "what"
- [ ] Tests cover main functionality
- [ ] No hardcoded values
- [ ] Proper use of interfaces
- [ ] Efficient use of goroutines and channels
- [ ] Security considerations addressed
- [ ] Performance implications considered

---

## 🛠️ Tools

### Essential Tools
```bash
# Code formatting
gofmt -w .

# Code linting
golangci-lint run

# Security scanning
gosec ./...

# Dependency vulnerability scanning
nancy sleuth

# Performance profiling
go test -bench=. -cpuprofile=cpu.prof
go tool pprof cpu.prof
```

### IDE Configuration
```json
// .vscode/settings.json
{
    "go.formatTool": "gofmt",
    "go.lintTool": "golangci-lint",
    "go.lintFlags": ["--fast"],
    "go.testFlags": ["-v"],
    "go.buildTags": "debug"
}
```

---

## 📚 Additional Resources

- [Effective Go](https://golang.org/doc/effective_go.html)
- [Go Code Review Comments](https://github.com/golang/go/wiki/CodeReviewComments)
- [Go Blog](https://blog.golang.org/)
- [Go by Example](https://gobyexample.com/)
- [Go Web Examples](https://gowebexamples.com/)

---

*Remember: The best code is code that is easy to read, understand, and maintain. Always prioritize clarity over cleverness.*