# Project 6: Web Development

## 🎯 Learning Objectives

- Build HTTP servers using the `net/http` package
- Handle different HTTP methods (GET, POST, PUT, DELETE)
- Work with JSON data and REST APIs
- Implement middleware for request processing
- Use templates for dynamic HTML generation
- Handle file uploads and static file serving
- Implement basic authentication and sessions

## 📚 Concepts Covered

- **HTTP Server**: Creating and configuring web servers
- **HTTP Handlers**: Processing HTTP requests and responses
- **REST APIs**: Building RESTful web services
- **JSON Processing**: Working with JSON data
- **Middleware**: Request/response processing pipeline
- **Templates**: Dynamic HTML generation
- **Static Files**: Serving static assets
- **Sessions**: User session management

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 06-web-development`
2. Initialize Go module: `go mod init web-development`
3. Create your Go file: `main.go`

### Dependencies
```bash
go get github.com/gorilla/mux
go get github.com/gorilla/sessions
```

## 📝 Exercises

### Exercise 1: Basic HTTP Server
Create a simple HTTP server with multiple endpoints.

**Expected Output:**
```
Server starting on :8080...
GET / - Hello, World!
GET /health - {"status": "healthy"}
GET /api/users - [{"id": 1, "name": "John"}, {"id": 2, "name": "Jane"}]
```

### Exercise 2: REST API
Build a RESTful API for managing a simple resource.

**Expected Output:**
```
POST /api/items - Create new item
GET /api/items - List all items
GET /api/items/1 - Get specific item
PUT /api/items/1 - Update item
DELETE /api/items/1 - Delete item
```

### Exercise 3: HTML Templates
Create dynamic HTML pages using Go templates.

**Expected Output:**
```
GET / - Renders index.html with dynamic data
GET /users - Renders users.html with user list
GET /form - Renders form.html for data input
```

### Exercise 4: File Upload
Implement file upload functionality.

**Expected Output:**
```
POST /upload - Accept file uploads
GET /files - List uploaded files
GET /files/{filename} - Download specific file
```

## 🔧 Code Examples

### Basic HTTP Server
```go
package main

import (
    "fmt"
    "net/http"
)

func helloHandler(w http.ResponseWriter, r *http.Request) {
    fmt.Fprintf(w, "Hello, World!")
}

func main() {
    http.HandleFunc("/", helloHandler)
    http.ListenAndServe(":8080", nil)
}
```

### REST API with JSON
```go
type User struct {
    ID   int    `json:"id"`
    Name string `json:"name"`
    Email string `json:"email"`
}

func usersHandler(w http.ResponseWriter, r *http.Request) {
    users := []User{
        {ID: 1, Name: "John", Email: "john@example.com"},
        {ID: 2, Name: "Jane", Email: "jane@example.com"},
    }
    
    w.Header().Set("Content-Type", "application/json")
    json.NewEncoder(w).Encode(users)
}
```

### Middleware Example
```go
func loggingMiddleware(next http.Handler) http.Handler {
    return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
        fmt.Printf("Request: %s %s\n", r.Method, r.URL.Path)
        next.ServeHTTP(w, r)
    })
}
```

### HTML Template
```go
const htmlTemplate = `
<!DOCTYPE html>
<html>
<head><title>{{.Title}}</title></head>
<body>
    <h1>{{.Title}}</h1>
    <p>{{.Message}}</p>
</body>
</html>
`

func templateHandler(w http.ResponseWriter, r *http.Request) {
    data := struct {
        Title   string
        Message string
    }{
        Title:   "Welcome",
        Message: "Hello from Go template!",
    }
    
    tmpl := template.Must(template.New("page").Parse(htmlTemplate))
    tmpl.Execute(w, data)
}
```

## 🧪 Testing Your Code

Run your server:
```bash
go run main.go
```

Test endpoints:
```bash
curl http://localhost:8080/
curl http://localhost:8080/api/users
curl -X POST http://localhost:8080/api/items -d '{"name":"test"}'
```

## 📁 Project Structure

```
06-web-development/
├── main.go
├── handlers/
│   ├── user.go
│   ├── item.go
│   └── file.go
├── middleware/
│   ├── logging.go
│   └── auth.go
├── templates/
│   ├── index.html
│   ├── users.html
│   └── form.html
├── static/
│   ├── css/
│   └── js/
└── uploads/
```

## 📋 Checklist

- [ ] Created basic HTTP server
- [ ] Implemented REST API endpoints
- [ ] Added JSON request/response handling
- [ ] Created HTML templates
- [ ] Implemented middleware
- [ ] Added file upload functionality
- [ ] Served static files
- [ ] Added basic error handling
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- How to build HTTP servers in Go
- REST API design and implementation
- Template-based HTML generation
- Middleware patterns
- File handling in web applications

## 🔗 Related Resources

- [Go Web Examples](https://gowebexamples.com/)
- [Gorilla Web Toolkit](https://www.gorillatoolkit.org/)
- [Go HTTP Package](https://golang.org/pkg/net/http/)