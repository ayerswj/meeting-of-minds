package main

import (
	"encoding/json"
	"fmt"
	"html/template"
	"io"
	"log"
	"net/http"
	"os"
	"path/filepath"
	"strconv"
	"time"
)

// Data structures
type User struct {
	ID    int    `json:"id"`
	Name  string `json:"name"`
	Email string `json:"email"`
	Age   int    `json:"age"`
}

type Item struct {
	ID          int       `json:"id"`
	Name        string    `json:"name"`
	Description string    `json:"description"`
	Price       float64   `json:"price"`
	CreatedAt   time.Time `json:"created_at"`
}

type Response struct {
	Success bool        `json:"success"`
	Message string      `json:"message"`
	Data    interface{} `json:"data,omitempty"`
}

// Global storage (in production, use a database)
var users = []User{
	{ID: 1, Name: "John Doe", Email: "john@example.com", Age: 30},
	{ID: 2, Name: "Jane Smith", Email: "jane@example.com", Age: 25},
	{ID: 3, Name: "Bob Johnson", Email: "bob@example.com", Age: 35},
}

var items = []Item{
	{ID: 1, Name: "Laptop", Description: "High-performance laptop", Price: 999.99, CreatedAt: time.Now()},
	{ID: 2, Name: "Mouse", Description: "Wireless mouse", Price: 29.99, CreatedAt: time.Now()},
	{ID: 3, Name: "Keyboard", Description: "Mechanical keyboard", Price: 89.99, CreatedAt: time.Now()},
}

var nextUserID = 4
var nextItemID = 4

func main() {
	fmt.Println("=== Go Web Development Project ===")
	fmt.Println("Server starting on :8080...")

	// Basic routes
	http.HandleFunc("/", homeHandler)
	http.HandleFunc("/health", healthHandler)
	http.HandleFunc("/api/users", usersHandler)
	http.HandleFunc("/api/items", itemsHandler)

	// Template routes
	http.HandleFunc("/users", usersPageHandler)
	http.HandleFunc("/form", formHandler)
	http.HandleFunc("/submit", submitHandler)

	// File upload routes
	http.HandleFunc("/upload", uploadHandler)
	http.HandleFunc("/files", filesHandler)
	http.HandleFunc("/files/", fileDownloadHandler)

	// Static file serving
	fs := http.FileServer(http.Dir("static"))
	http.Handle("/static/", http.StripPrefix("/static/", fs))

	// Start server
	log.Fatal(http.ListenAndServe(":8080", nil))
}

// Basic HTTP handlers
func homeHandler(w http.ResponseWriter, r *http.Request) {
	if r.URL.Path != "/" {
		http.NotFound(w, r)
		return
	}

	// HTML template
	tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>Go Web Development</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 800px; margin: 0 auto; }
        .nav { background: #f0f0f0; padding: 10px; margin-bottom: 20px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #333; }
        .nav a:hover { color: #007bff; }
        .card { border: 1px solid #ddd; padding: 20px; margin: 10px 0; border-radius: 5px; }
        .btn { background: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 3px; cursor: pointer; }
        .btn:hover { background: #0056b3; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="/">Home</a>
            <a href="/users">Users</a>
            <a href="/form">Form</a>
            <a href="/upload">Upload</a>
            <a href="/api/users">API Users</a>
            <a href="/api/items">API Items</a>
        </div>
        
        <h1>Welcome to Go Web Development</h1>
        <p>This is a comprehensive web application built with Go.</p>
        
        <div class="card">
            <h2>Features</h2>
            <ul>
                <li>REST API endpoints</li>
                <li>HTML templates</li>
                <li>File upload handling</li>
                <li>Static file serving</li>
                <li>JSON responses</li>
            </ul>
        </div>
        
        <div class="card">
            <h2>Quick Links</h2>
            <a href="/api/users" class="btn">View Users API</a>
            <a href="/api/items" class="btn">View Items API</a>
            <a href="/users" class="btn">View Users Page</a>
        </div>
    </div>
</body>
</html>`

	t, err := template.New("home").Parse(tmpl)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	t.Execute(w, nil)
}

func healthHandler(w http.ResponseWriter, r *http.Request) {
	response := Response{
		Success: true,
		Message: "Service is healthy",
		Data: map[string]interface{}{
			"timestamp": time.Now(),
			"status":    "ok",
		},
	}

	w.Header().Set("Content-Type", "application/json")
	json.NewEncoder(w).Encode(response)
}

// REST API handlers
func usersHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	switch r.Method {
	case "GET":
		// Get all users or specific user
		if id := r.URL.Query().Get("id"); id != "" {
			userID, err := strconv.Atoi(id)
			if err != nil {
				http.Error(w, "Invalid user ID", http.StatusBadRequest)
				return
			}

			for _, user := range users {
				if user.ID == userID {
					json.NewEncoder(w).Encode(Response{
						Success: true,
						Message: "User found",
						Data:    user,
					})
					return
				}
			}
			http.Error(w, "User not found", http.StatusNotFound)
			return
		}

		// Return all users
		json.NewEncoder(w).Encode(Response{
			Success: true,
			Message: "Users retrieved successfully",
			Data:    users,
		})

	case "POST":
		// Create new user
		var newUser User
		if err := json.NewDecoder(r.Body).Decode(&newUser); err != nil {
			http.Error(w, "Invalid JSON", http.StatusBadRequest)
			return
		}

		newUser.ID = nextUserID
		nextUserID++
		users = append(users, newUser)

		json.NewEncoder(w).Encode(Response{
			Success: true,
			Message: "User created successfully",
			Data:    newUser,
		})

	case "PUT":
		// Update user
		var updateUser User
		if err := json.NewDecoder(r.Body).Decode(&updateUser); err != nil {
			http.Error(w, "Invalid JSON", http.StatusBadRequest)
			return
		}

		for i, user := range users {
			if user.ID == updateUser.ID {
				users[i] = updateUser
				json.NewEncoder(w).Encode(Response{
					Success: true,
					Message: "User updated successfully",
					Data:    updateUser,
				})
				return
			}
		}
		http.Error(w, "User not found", http.StatusNotFound)

	case "DELETE":
		// Delete user
		if id := r.URL.Query().Get("id"); id != "" {
			userID, err := strconv.Atoi(id)
			if err != nil {
				http.Error(w, "Invalid user ID", http.StatusBadRequest)
				return
			}

			for i, user := range users {
				if user.ID == userID {
					users = append(users[:i], users[i+1:]...)
					json.NewEncoder(w).Encode(Response{
						Success: true,
						Message: "User deleted successfully",
					})
					return
				}
			}
			http.Error(w, "User not found", http.StatusNotFound)
		}

	default:
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
	}
}

func itemsHandler(w http.ResponseWriter, r *http.Request) {
	w.Header().Set("Content-Type", "application/json")

	switch r.Method {
	case "GET":
		// Get all items or specific item
		if id := r.URL.Query().Get("id"); id != "" {
			itemID, err := strconv.Atoi(id)
			if err != nil {
				http.Error(w, "Invalid item ID", http.StatusBadRequest)
				return
			}

			for _, item := range items {
				if item.ID == itemID {
					json.NewEncoder(w).Encode(Response{
						Success: true,
						Message: "Item found",
						Data:    item,
					})
					return
				}
			}
			http.Error(w, "Item not found", http.StatusNotFound)
			return
		}

		// Return all items
		json.NewEncoder(w).Encode(Response{
			Success: true,
			Message: "Items retrieved successfully",
			Data:    items,
		})

	case "POST":
		// Create new item
		var newItem Item
		if err := json.NewDecoder(r.Body).Decode(&newItem); err != nil {
			http.Error(w, "Invalid JSON", http.StatusBadRequest)
			return
		}

		newItem.ID = nextItemID
		nextItemID++
		newItem.CreatedAt = time.Now()
		items = append(items, newItem)

		json.NewEncoder(w).Encode(Response{
			Success: true,
			Message: "Item created successfully",
			Data:    newItem,
		})

	default:
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
	}
}

// Template handlers
func usersPageHandler(w http.ResponseWriter, r *http.Request) {
	tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 800px; margin: 0 auto; }
        .nav { background: #f0f0f0; padding: 10px; margin-bottom: 20px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #333; }
        .user-card { border: 1px solid #ddd; padding: 15px; margin: 10px 0; border-radius: 5px; }
        .user-name { font-weight: bold; color: #007bff; }
        .user-email { color: #666; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="/">Home</a>
            <a href="/users">Users</a>
            <a href="/form">Form</a>
            <a href="/upload">Upload</a>
        </div>
        
        <h1>Users</h1>
        {{range .}}
        <div class="user-card">
            <div class="user-name">{{.Name}}</div>
            <div class="user-email">{{.Email}}</div>
            <div>Age: {{.Age}}</div>
            <div>ID: {{.ID}}</div>
        </div>
        {{end}}
    </div>
</body>
</html>`

	t, err := template.New("users").Parse(tmpl)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	t.Execute(w, users)
}

func formHandler(w http.ResponseWriter, r *http.Request) {
	tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 600px; margin: 0 auto; }
        .nav { background: #f0f0f0; padding: 10px; margin-bottom: 20px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #333; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input, textarea { width: 100%; padding: 8px; border: 1px solid #ddd; border-radius: 3px; }
        .btn { background: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 3px; cursor: pointer; }
        .btn:hover { background: #0056b3; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="/">Home</a>
            <a href="/users">Users</a>
            <a href="/form">Form</a>
            <a href="/upload">Upload</a>
        </div>
        
        <h1>Contact Form</h1>
        <form action="/submit" method="POST">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="form-group">
                <label for="message">Message:</label>
                <textarea id="message" name="message" rows="4" required></textarea>
            </div>
            
            <button type="submit" class="btn">Submit</button>
        </form>
    </div>
</body>
</html>`

	t, err := template.New("form").Parse(tmpl)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	t.Execute(w, nil)
}

func submitHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method != "POST" {
		http.Error(w, "Method not allowed", http.StatusMethodNotAllowed)
		return
	}

	// Parse form data
	if err := r.ParseForm(); err != nil {
		http.Error(w, "Error parsing form", http.StatusBadRequest)
		return
	}

	name := r.FormValue("name")
	email := r.FormValue("email")
	message := r.FormValue("message")

	// In a real application, you would save this data to a database
	fmt.Printf("Form submission: Name=%s, Email=%s, Message=%s\n", name, email, message)

	// Return success response
	tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>Success</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 600px; margin: 0 auto; }
        .success { background: #d4edda; border: 1px solid #c3e6cb; padding: 15px; border-radius: 5px; color: #155724; }
        .btn { background: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 3px; cursor: pointer; text-decoration: none; display: inline-block; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="success">
            <h2>Form Submitted Successfully!</h2>
            <p>Thank you for your submission, {{.Name}}.</p>
            <p>We'll get back to you at {{.Email}} soon.</p>
        </div>
        <a href="/" class="btn">Back to Home</a>
    </div>
</body>
</html>`

	data := struct {
		Name  string
		Email string
	}{
		Name:  name,
		Email: email,
	}

	t, err := template.New("success").Parse(tmpl)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	t.Execute(w, data)
}

// File upload handlers
func uploadHandler(w http.ResponseWriter, r *http.Request) {
	if r.Method == "GET" {
		tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>File Upload</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 600px; margin: 0 auto; }
        .nav { background: #f0f0f0; padding: 10px; margin-bottom: 20px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #333; }
        .form-group { margin-bottom: 15px; }
        .btn { background: #007bff; color: white; padding: 10px 20px; border: none; border-radius: 3px; cursor: pointer; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="/">Home</a>
            <a href="/users">Users</a>
            <a href="/form">Form</a>
            <a href="/upload">Upload</a>
        </div>
        
        <h1>File Upload</h1>
        <form action="/upload" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="file">Select file:</label>
                <input type="file" id="file" name="file" required>
            </div>
            <button type="submit" class="btn">Upload</button>
        </form>
        
        <p><a href="/files">View uploaded files</a></p>
    </div>
</body>
</html>`

		t, err := template.New("upload").Parse(tmpl)
		if err != nil {
			http.Error(w, err.Error(), http.StatusInternalServerError)
			return
		}
		t.Execute(w, nil)
		return
	}

	if r.Method == "POST" {
		// Parse multipart form
		if err := r.ParseMultipartForm(32 << 20); err != nil {
			http.Error(w, "Error parsing form", http.StatusBadRequest)
			return
		}

		file, handler, err := r.FormFile("file")
		if err != nil {
			http.Error(w, "Error retrieving file", http.StatusBadRequest)
			return
		}
		defer file.Close()

		// Create uploads directory if it doesn't exist
		if err := os.MkdirAll("uploads", 0755); err != nil {
			http.Error(w, "Error creating upload directory", http.StatusInternalServerError)
			return
		}

		// Create file on server
		dst, err := os.Create(filepath.Join("uploads", handler.Filename))
		if err != nil {
			http.Error(w, "Error creating file", http.StatusInternalServerError)
			return
		}
		defer dst.Close()

		// Copy uploaded file to destination
		if _, err := io.Copy(dst, file); err != nil {
			http.Error(w, "Error saving file", http.StatusInternalServerError)
			return
		}

		fmt.Fprintf(w, "File %s uploaded successfully!", handler.Filename)
	}
}

func filesHandler(w http.ResponseWriter, r *http.Request) {
	// List uploaded files
	files, err := os.ReadDir("uploads")
	if err != nil {
		http.Error(w, "Error reading uploads directory", http.StatusInternalServerError)
		return
	}

	tmpl := `
<!DOCTYPE html>
<html>
<head>
    <title>Uploaded Files</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 800px; margin: 0 auto; }
        .nav { background: #f0f0f0; padding: 10px; margin-bottom: 20px; }
        .nav a { margin-right: 20px; text-decoration: none; color: #333; }
        .file-item { border: 1px solid #ddd; padding: 10px; margin: 5px 0; border-radius: 3px; }
        .file-link { color: #007bff; text-decoration: none; }
        .file-link:hover { text-decoration: underline; }
    </style>
</head>
<body>
    <div class="container">
        <div class="nav">
            <a href="/">Home</a>
            <a href="/users">Users</a>
            <a href="/form">Form</a>
            <a href="/upload">Upload</a>
        </div>
        
        <h1>Uploaded Files</h1>
        {{if .}}
            {{range .}}
            <div class="file-item">
                <a href="/files/{{.}}" class="file-link">{{.}}</a>
            </div>
            {{end}}
        {{else}}
            <p>No files uploaded yet.</p>
        {{end}}
        
        <p><a href="/upload">Upload new file</a></p>
    </div>
</body>
</html>`

	var fileNames []string
	for _, file := range files {
		if !file.IsDir() {
			fileNames = append(fileNames, file.Name())
		}
	}

	t, err := template.New("files").Parse(tmpl)
	if err != nil {
		http.Error(w, err.Error(), http.StatusInternalServerError)
		return
	}

	t.Execute(w, fileNames)
}

func fileDownloadHandler(w http.ResponseWriter, r *http.Request) {
	// Extract filename from URL
	filename := filepath.Base(r.URL.Path)
	filepath := filepath.Join("uploads", filename)

	// Check if file exists
	if _, err := os.Stat(filepath); os.IsNotExist(err) {
		http.NotFound(w, r)
		return
	}

	// Serve file
	http.ServeFile(w, r, filepath)
}