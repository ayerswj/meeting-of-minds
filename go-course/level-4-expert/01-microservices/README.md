# Project 1: Microservices Architecture

## 🎯 Learning Objectives

- Design and implement microservices architecture
- Use service discovery and load balancing
- Implement inter-service communication
- Handle distributed transactions and eventual consistency
- Use message queues for asynchronous communication
- Implement API gateways and circuit breakers
- Deploy and monitor microservices

## 📚 Concepts Covered

- **Microservices Design**: Breaking monoliths into services
- **Service Discovery**: Dynamic service registration and discovery
- **Load Balancing**: Distributing requests across service instances
- **API Gateway**: Centralized request routing and processing
- **Message Queues**: Asynchronous communication patterns
- **Circuit Breakers**: Fault tolerance and resilience
- **Distributed Tracing**: Request tracking across services
- **Container Orchestration**: Managing service deployment

## 🚀 Getting Started

### Setup
1. Navigate to this directory: `cd 01-microservices`
2. Initialize Go module: `go mod init microservices`
3. Create service directories and files

### Dependencies
```bash
go get github.com/gorilla/mux
go get github.com/go-redis/redis/v8
go get github.com/streadway/amqp
go get github.com/hashicorp/consul/api
go get github.com/sirupsen/logrus
```

## 📝 Exercises

### Exercise 1: User Service
Create a microservice for user management.

**Expected Output:**
```
User Service starting on :8081...
POST /users - Create user
GET /users/{id} - Get user
PUT /users/{id} - Update user
DELETE /users/{id} - Delete user
```

### Exercise 2: Order Service
Create a microservice for order processing.

**Expected Output:**
```
Order Service starting on :8082...
POST /orders - Create order
GET /orders/{id} - Get order
PUT /orders/{id} - Update order status
GET /orders/user/{userId} - Get user orders
```

### Exercise 3: API Gateway
Implement an API gateway to route requests.

**Expected Output:**
```
API Gateway starting on :8080...
GET /api/users/{id} - Route to User Service
GET /api/orders/{id} - Route to Order Service
POST /api/orders - Route to Order Service
```

### Exercise 4: Service Discovery
Implement service discovery using Consul.

**Expected Output:**
```
Service Discovery:
- User Service registered at :8081
- Order Service registered at :8082
- API Gateway registered at :8080
```

## 🔧 Code Examples

### User Service
```go
package main

import (
    "encoding/json"
    "net/http"
    "github.com/gorilla/mux"
)

type User struct {
    ID    string `json:"id"`
    Name  string `json:"name"`
    Email string `json:"email"`
}

type UserService struct {
    users map[string]User
}

func (s *UserService) createUser(w http.ResponseWriter, r *http.Request) {
    var user User
    json.NewDecoder(r.Body).Decode(&user)
    
    user.ID = generateID()
    s.users[user.ID] = user
    
    w.Header().Set("Content-Type", "application/json")
    json.NewEncoder(w).Encode(user)
}

func main() {
    service := &UserService{users: make(map[string]User)}
    
    r := mux.NewRouter()
    r.HandleFunc("/users", service.createUser).Methods("POST")
    r.HandleFunc("/users/{id}", service.getUser).Methods("GET")
    
    http.ListenAndServe(":8081", r)
}
```

### API Gateway
```go
package main

import (
    "net/http"
    "net/http/httputil"
    "net/url"
)

type Gateway struct {
    userServiceURL  string
    orderServiceURL string
}

func (g *Gateway) userHandler(w http.ResponseWriter, r *http.Request) {
    target, _ := url.Parse(g.userServiceURL)
    proxy := httputil.NewSingleHostReverseProxy(target)
    proxy.ServeHTTP(w, r)
}

func (g *Gateway) orderHandler(w http.ResponseWriter, r *http.Request) {
    target, _ := url.Parse(g.orderServiceURL)
    proxy := httputil.NewSingleHostReverseProxy(target)
    proxy.ServeHTTP(w, r)
}

func main() {
    gateway := &Gateway{
        userServiceURL:  "http://localhost:8081",
        orderServiceURL: "http://localhost:8082",
    }
    
    http.HandleFunc("/api/users/", gateway.userHandler)
    http.HandleFunc("/api/orders/", gateway.orderHandler)
    
    http.ListenAndServe(":8080", nil)
}
```

### Service Discovery with Consul
```go
package main

import (
    "fmt"
    "github.com/hashicorp/consul/api"
)

func registerService(client *api.Client, serviceName, serviceID, address string, port int) error {
    registration := &api.AgentServiceRegistration{
        ID:      serviceID,
        Name:    serviceName,
        Address: address,
        Port:    port,
    }
    
    return client.Agent().ServiceRegister(registration)
}

func discoverService(client *api.Client, serviceName string) (string, error) {
    services, _, err := client.Health().Service(serviceName, "", true, nil)
    if err != nil {
        return "", err
    }
    
    if len(services) > 0 {
        service := services[0]
        return fmt.Sprintf("http://%s:%d", service.Service.Address, service.Service.Port), nil
    }
    
    return "", fmt.Errorf("service %s not found", serviceName)
}
```

## 📁 Project Structure

```
01-microservices/
├── user-service/
│   ├── main.go
│   ├── handlers.go
│   └── models.go
├── order-service/
│   ├── main.go
│   ├── handlers.go
│   └── models.go
├── api-gateway/
│   ├── main.go
│   ├── routes.go
│   └── middleware.go
├── service-discovery/
│   ├── consul.go
│   └── registry.go
├── shared/
│   ├── models.go
│   └── utils.go
├── docker-compose.yml
└── README.md
```

## 🧪 Testing Your Code

Start services:
```bash
# Terminal 1
cd user-service && go run main.go

# Terminal 2
cd order-service && go run main.go

# Terminal 3
cd api-gateway && go run main.go
```

Test API:
```bash
# Create user
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com"}'

# Create order
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":"1","items":["item1","item2"]}'
```

## 📋 Checklist

- [ ] Created user microservice
- [ ] Created order microservice
- [ ] Implemented API gateway
- [ ] Added service discovery
- [ ] Implemented load balancing
- [ ] Added circuit breaker pattern
- [ ] Created Docker containers
- [ ] Added monitoring and logging
- [ ] Completed all exercises

## 🎓 Key Takeaways

After completing this project, you should understand:
- Microservices architecture principles
- Service-to-service communication patterns
- API gateway implementation
- Service discovery and load balancing
- Distributed system challenges

## 🔗 Related Resources

- [Go Microservices](https://github.com/go-micro/go-micro)
- [Consul Documentation](https://www.consul.io/docs)
- [Docker Compose](https://docs.docker.com/compose/)
- [Circuit Breaker Pattern](https://martinfowler.com/bliki/CircuitBreaker.html)