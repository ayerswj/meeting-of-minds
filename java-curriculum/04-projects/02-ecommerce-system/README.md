# Project 2: E-commerce Management System

## Project Overview
Build a comprehensive E-commerce Management System that demonstrates advanced Java concepts including OOP, collections, exception handling, file I/O, and design patterns. This project simulates a real-world e-commerce platform with inventory management, order processing, customer management, and reporting capabilities.

## Learning Objectives
- Apply advanced OOP principles in a complex system
- Implement design patterns (Factory, Observer, Strategy, Singleton)
- Practice comprehensive exception handling
- Use collections framework effectively
- Implement data persistence and serialization
- Create a modular, extensible architecture
- Apply SOLID principles and clean code practices

## System Architecture

### Core Modules
```
E-commerce System/
├── Core Domain/
│   ├── Product Management
│   ├── Inventory Management
│   ├── Order Processing
│   ├── Customer Management
│   └── Payment Processing
├── Infrastructure/
│   ├── Data Persistence
│   ├── File I/O Operations
│   └── Configuration Management
├── Business Logic/
│   ├── Pricing Strategies
│   ├── Discount Management
│   ├── Shipping Calculation
│   └── Order Validation
├── User Interface/
│   ├── Console Interface
│   ├── Menu System
│   └── Report Generation
└── Utilities/
    ├── Logging
    ├── Validation
    └── Helper Classes
```

## Domain Models

### Product Management
```java
public abstract class Product {
    protected String productId;
    protected String name;
    protected String description;
    protected BigDecimal price;
    protected ProductCategory category;
    protected int stockQuantity;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
    
    // Abstract methods for different product types
    public abstract String getProductType();
    public abstract Map<String, Object> getProductDetails();
    
    // Common methods
    public boolean isInStock() {
        return stockQuantity > 0;
    }
    
    public void updateStock(int quantity) {
        this.stockQuantity = Math.max(0, this.stockQuantity + quantity);
        this.updatedAt = LocalDateTime.now();
    }
}

public class Electronics extends Product {
    private String brand;
    private String model;
    private int warrantyMonths;
    private Map<String, String> specifications;
    
    @Override
    public String getProductType() {
        return "Electronics";
    }
    
    @Override
    public Map<String, Object> getProductDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("brand", brand);
        details.put("model", model);
        details.put("warrantyMonths", warrantyMonths);
        details.put("specifications", specifications);
        return details;
    }
}

public class Clothing extends Product {
    private String size;
    private String color;
    private String material;
    private Gender gender;
    
    @Override
    public String getProductType() {
        return "Clothing";
    }
    
    @Override
    public Map<String, Object> getProductDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("size", size);
        details.put("color", color);
        details.put("material", material);
        details.put("gender", gender);
        return details;
    }
}
```

### Customer Management
```java
public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    private LocalDateTime registrationDate;
    private CustomerStatus status;
    private List<Order> orderHistory;
    private CustomerPreferences preferences;
    
    public Customer(String firstName, String lastName, String email) {
        this.customerId = generateCustomerId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registrationDate = LocalDateTime.now();
        this.status = CustomerStatus.ACTIVE;
        this.orderHistory = new ArrayList<>();
        this.preferences = new CustomerPreferences();
    }
    
    public void addOrder(Order order) {
        orderHistory.add(order);
    }
    
    public double getTotalSpent() {
        return orderHistory.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }
    
    public int getOrderCount() {
        return orderHistory.size();
    }
    
    public CustomerTier getCustomerTier() {
        double totalSpent = getTotalSpent();
        if (totalSpent >= 1000) return CustomerTier.PLATINUM;
        if (totalSpent >= 500) return CustomerTier.GOLD;
        if (totalSpent >= 100) return CustomerTier.SILVER;
        return CustomerTier.BRONZE;
    }
}

public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    
    public boolean isValid() {
        return street != null && !street.trim().isEmpty() &&
               city != null && !city.trim().isEmpty() &&
               state != null && !state.trim().isEmpty() &&
               zipCode != null && !zipCode.trim().isEmpty() &&
               country != null && !country.trim().isEmpty();
    }
}
```

### Order Processing
```java
public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private LocalDateTime lastUpdated;
    private Address shippingAddress;
    private PaymentMethod paymentMethod;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private String notes;
    
    public Order(Customer customer) {
        this.orderId = generateOrderId();
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        this.orderDate = LocalDateTime.now();
        this.lastUpdated = LocalDateTime.now();
        this.shippingAddress = customer.getAddress();
    }
    
    public void addItem(Product product, int quantity) {
        if (product.isInStock() && product.getStockQuantity() >= quantity) {
            OrderItem item = new OrderItem(product, quantity);
            items.add(item);
            product.updateStock(-quantity);
            calculateTotals();
        } else {
            throw new InsufficientStockException(
                "Insufficient stock for product: " + product.getName());
        }
    }
    
    public void removeItem(String productId) {
        items.removeIf(item -> item.getProduct().getProductId().equals(productId));
        calculateTotals();
    }
    
    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        this.lastUpdated = LocalDateTime.now();
        notifyStatusChange(newStatus);
    }
    
    private void calculateTotals() {
        this.subtotal = items.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        this.tax = subtotal.multiply(new BigDecimal("0.08")); // 8% tax
        this.shippingCost = calculateShippingCost();
        this.totalAmount = subtotal.add(tax).add(shippingCost).subtract(discount);
    }
    
    private BigDecimal calculateShippingCost() {
        // Implement shipping calculation logic
        return new BigDecimal("10.00");
    }
    
    private void notifyStatusChange(OrderStatus status) {
        // Implement observer pattern for status notifications
    }
}

public class OrderItem {
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
    
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.subtotal = unitPrice.multiply(new BigDecimal(quantity));
    }
}
```

## Design Patterns Implementation

### Factory Pattern - Product Creation
```java
public class ProductFactory {
    public static Product createProduct(ProductType type, Map<String, Object> properties) {
        switch (type) {
            case ELECTRONICS:
                return createElectronics(properties);
            case CLOTHING:
                return createClothing(properties);
            case BOOKS:
                return createBooks(properties);
            default:
                throw new IllegalArgumentException("Unknown product type: " + type);
        }
    }
    
    private static Electronics createElectronics(Map<String, Object> properties) {
        Electronics electronics = new Electronics();
        electronics.setBrand((String) properties.get("brand"));
        electronics.setModel((String) properties.get("model"));
        electronics.setWarrantyMonths((Integer) properties.get("warrantyMonths"));
        electronics.setSpecifications((Map<String, String>) properties.get("specifications"));
        return electronics;
    }
    
    private static Clothing createClothing(Map<String, Object> properties) {
        Clothing clothing = new Clothing();
        clothing.setSize((String) properties.get("size"));
        clothing.setColor((String) properties.get("color"));
        clothing.setMaterial((String) properties.get("material"));
        clothing.setGender((Gender) properties.get("gender"));
        return clothing;
    }
}
```

### Observer Pattern - Order Status Notifications
```java
public interface OrderObserver {
    void onOrderStatusChanged(Order order, OrderStatus oldStatus, OrderStatus newStatus);
}

public class OrderSubject {
    private List<OrderObserver> observers = new ArrayList<>();
    
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(Order order, OrderStatus oldStatus, OrderStatus newStatus) {
        for (OrderObserver observer : observers) {
            observer.onOrderStatusChanged(order, oldStatus, newStatus);
        }
    }
}

public class EmailNotificationService implements OrderObserver {
    @Override
    public void onOrderStatusChanged(Order order, OrderStatus oldStatus, OrderStatus newStatus) {
        String message = String.format(
            "Order %s status changed from %s to %s",
            order.getOrderId(), oldStatus, newStatus);
        sendEmail(order.getCustomer().getEmail(), "Order Status Update", message);
    }
    
    private void sendEmail(String email, String subject, String message) {
        // Implement email sending logic
        System.out.println("Sending email to " + email + ": " + subject);
    }
}
```

### Strategy Pattern - Pricing and Discounts
```java
public interface PricingStrategy {
    BigDecimal calculatePrice(Product product, int quantity);
}

public class RegularPricingStrategy implements PricingStrategy {
    @Override
    public BigDecimal calculatePrice(Product product, int quantity) {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }
}

public class BulkDiscountStrategy implements PricingStrategy {
    private static final Map<Integer, Double> DISCOUNT_RATES = Map.of(
        5, 0.05,   // 5% discount for 5+ items
        10, 0.10,  // 10% discount for 10+ items
        20, 0.15   // 15% discount for 20+ items
    );
    
    @Override
    public BigDecimal calculatePrice(Product product, int quantity) {
        BigDecimal basePrice = product.getPrice().multiply(new BigDecimal(quantity));
        
        double discountRate = DISCOUNT_RATES.entrySet().stream()
                .filter(entry -> quantity >= entry.getKey())
                .mapToDouble(Map.Entry::getValue)
                .max()
                .orElse(0.0);
        
        BigDecimal discount = basePrice.multiply(new BigDecimal(discountRate));
        return basePrice.subtract(discount);
    }
}

public class SeasonalDiscountStrategy implements PricingStrategy {
    private final double seasonalDiscountRate;
    
    public SeasonalDiscountStrategy(double seasonalDiscountRate) {
        this.seasonalDiscountRate = seasonalDiscountRate;
    }
    
    @Override
    public BigDecimal calculatePrice(Product product, int quantity) {
        BigDecimal basePrice = product.getPrice().multiply(new BigDecimal(quantity));
        BigDecimal discount = basePrice.multiply(new BigDecimal(seasonalDiscountRate));
        return basePrice.subtract(discount);
    }
}
```

### Singleton Pattern - Configuration Management
```java
public class ConfigurationManager {
    private static ConfigurationManager instance;
    private Properties properties;
    
    private ConfigurationManager() {
        loadConfiguration();
    }
    
    public static synchronized ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }
    
    private void loadConfiguration() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            throw new ConfigurationException("Failed to load configuration", e);
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
```

## Data Persistence Layer

### File-Based Storage
```java
public class FileStorageManager {
    private static final String DATA_DIR = "data/";
    private static final String PRODUCTS_FILE = "products.dat";
    private static final String CUSTOMERS_FILE = "customers.dat";
    private static final String ORDERS_FILE = "orders.dat";
    
    public void saveProducts(List<Product> products) throws StorageException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_DIR + PRODUCTS_FILE))) {
            oos.writeObject(products);
        } catch (IOException e) {
            throw new StorageException("Failed to save products", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Product> loadProducts() throws StorageException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_DIR + PRODUCTS_FILE))) {
            return (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("Failed to load products", e);
        }
    }
    
    public void saveCustomers(List<Customer> customers) throws StorageException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_DIR + CUSTOMERS_FILE))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            throw new StorageException("Failed to save customers", e);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Customer> loadCustomers() throws StorageException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_DIR + CUSTOMERS_FILE))) {
            return (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new StorageException("Failed to load customers", e);
        }
    }
}
```

## Business Logic Layer

### Inventory Management
```java
public class InventoryManager {
    private Map<String, Product> products;
    private List<InventoryTransaction> transactions;
    private InventoryAlertService alertService;
    
    public InventoryManager() {
        this.products = new HashMap<>();
        this.transactions = new ArrayList<>();
        this.alertService = new InventoryAlertService();
    }
    
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
        logTransaction(product, "ADD", product.getStockQuantity());
    }
    
    public void updateStock(String productId, int quantity) {
        Product product = products.get(productId);
        if (product == null) {
            throw new ProductNotFoundException("Product not found: " + productId);
        }
        
        int oldQuantity = product.getStockQuantity();
        product.updateStock(quantity);
        
        logTransaction(product, "UPDATE", quantity);
        
        // Check for low stock alerts
        if (product.getStockQuantity() <= 10) {
            alertService.sendLowStockAlert(product);
        }
    }
    
    public List<Product> getLowStockProducts(int threshold) {
        return products.values().stream()
                .filter(product -> product.getStockQuantity() <= threshold)
                .collect(Collectors.toList());
    }
    
    public List<Product> searchProducts(String query) {
        return products.values().stream()
                .filter(product -> 
                    product.getName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getDescription().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    private void logTransaction(Product product, String operation, int quantity) {
        InventoryTransaction transaction = new InventoryTransaction(
            product.getProductId(), operation, quantity, LocalDateTime.now());
        transactions.add(transaction);
    }
}

public class InventoryTransaction {
    private String productId;
    private String operation;
    private int quantity;
    private LocalDateTime timestamp;
    
    public InventoryTransaction(String productId, String operation, 
                               int quantity, LocalDateTime timestamp) {
        this.productId = productId;
        this.operation = operation;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }
}
```

### Order Processing Service
```java
public class OrderProcessingService {
    private List<Order> orders;
    private InventoryManager inventoryManager;
    private PaymentProcessor paymentProcessor;
    private ShippingCalculator shippingCalculator;
    private OrderSubject orderSubject;
    
    public OrderProcessingService(InventoryManager inventoryManager) {
        this.orders = new ArrayList<>();
        this.inventoryManager = inventoryManager;
        this.paymentProcessor = new PaymentProcessor();
        this.shippingCalculator = new ShippingCalculator();
        this.orderSubject = new OrderSubject();
        
        // Register observers
        orderSubject.addObserver(new EmailNotificationService());
        orderSubject.addObserver(new InventoryUpdateService());
    }
    
    public Order createOrder(Customer customer) {
        Order order = new Order(customer);
        orders.add(order);
        return order;
    }
    
    public void processOrder(Order order) throws OrderProcessingException {
        try {
            // Validate order
            validateOrder(order);
            
            // Calculate shipping
            BigDecimal shippingCost = shippingCalculator.calculateShipping(order);
            order.setShippingCost(shippingCost);
            
            // Process payment
            PaymentResult paymentResult = paymentProcessor.processPayment(
                order.getPaymentMethod(), order.getTotalAmount());
            
            if (paymentResult.isSuccessful()) {
                order.updateStatus(OrderStatus.CONFIRMED);
                orderSubject.notifyObservers(order, OrderStatus.PENDING, OrderStatus.CONFIRMED);
            } else {
                throw new PaymentFailedException("Payment failed: " + paymentResult.getErrorMessage());
            }
            
        } catch (Exception e) {
            order.updateStatus(OrderStatus.FAILED);
            throw new OrderProcessingException("Failed to process order", e);
        }
    }
    
    private void validateOrder(Order order) throws OrderValidationException {
        if (order.getItems().isEmpty()) {
            throw new OrderValidationException("Order must contain at least one item");
        }
        
        if (order.getShippingAddress() == null || !order.getShippingAddress().isValid()) {
            throw new OrderValidationException("Invalid shipping address");
        }
        
        if (order.getPaymentMethod() == null) {
            throw new OrderValidationException("Payment method is required");
        }
        
        // Validate stock availability
        for (OrderItem item : order.getItems()) {
            Product product = inventoryManager.getProduct(item.getProduct().getProductId());
            if (product == null || !product.isInStock()) {
                throw new OrderValidationException(
                    "Product not available: " + item.getProduct().getName());
            }
        }
    }
    
    public List<Order> getOrdersByCustomer(String customerId) {
        return orders.stream()
                .filter(order -> order.getCustomer().getCustomerId().equals(customerId))
                .collect(Collectors.toList());
    }
    
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orders.stream()
                .filter(order -> order.getStatus() == status)
                .collect(Collectors.toList());
    }
}
```

## User Interface Layer

### Console Interface
```java
public class EcommerceConsole {
    private Scanner scanner;
    private InventoryManager inventoryManager;
    private OrderProcessingService orderService;
    private CustomerManager customerManager;
    private ReportGenerator reportGenerator;
    
    public EcommerceConsole() {
        this.scanner = new Scanner(System.in);
        this.inventoryManager = new InventoryManager();
        this.orderService = new OrderProcessingService(inventoryManager);
        this.customerManager = new CustomerManager();
        this.reportGenerator = new ReportGenerator();
        
        loadData();
    }
    
    public void start() {
        System.out.println("=== E-commerce Management System ===");
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            try {
                processMainMenuChoice(choice);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
    
    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Product Management");
        System.out.println("2. Customer Management");
        System.out.println("3. Order Processing");
        System.out.println("4. Inventory Management");
        System.out.println("5. Reports");
        System.out.println("6. Exit");
    }
    
    private void processMainMenuChoice(int choice) {
        switch (choice) {
            case 1:
                productManagementMenu();
                break;
            case 2:
                customerManagementMenu();
                break;
            case 3:
                orderProcessingMenu();
                break;
            case 4:
                inventoryManagementMenu();
                break;
            case 5:
                reportsMenu();
                break;
            case 6:
                saveData();
                System.out.println("Goodbye!");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
    private void productManagementMenu() {
        while (true) {
            System.out.println("\nProduct Management:");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Search Products");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Back to Main Menu");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewAllProducts();
                    break;
                case 3:
                    searchProducts();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void addProduct() {
        System.out.println("\nAdd New Product:");
        
        System.out.println("Product Types:");
        System.out.println("1. Electronics");
        System.out.println("2. Clothing");
        System.out.println("3. Books");
        
        int typeChoice = getIntInput("Select product type: ");
        ProductType type = ProductType.values()[typeChoice - 1];
        
        String name = getStringInput("Enter product name: ");
        String description = getStringInput("Enter product description: ");
        BigDecimal price = getBigDecimalInput("Enter product price: ");
        int stockQuantity = getIntInput("Enter stock quantity: ");
        
        Map<String, Object> properties = new HashMap<>();
        
        switch (type) {
            case ELECTRONICS:
                properties.put("brand", getStringInput("Enter brand: "));
                properties.put("model", getStringInput("Enter model: "));
                properties.put("warrantyMonths", getIntInput("Enter warranty months: "));
                break;
            case CLOTHING:
                properties.put("size", getStringInput("Enter size: "));
                properties.put("color", getStringInput("Enter color: "));
                properties.put("material", getStringInput("Enter material: "));
                break;
        }
        
        Product product = ProductFactory.createProduct(type, properties);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        
        inventoryManager.addProduct(product);
        System.out.println("Product added successfully!");
    }
    
    private void viewAllProducts() {
        List<Product> products = inventoryManager.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        
        System.out.println("\nAll Products:");
        System.out.printf("%-15s %-20s %-10s %-10s %-15s%n", 
            "ID", "Name", "Price", "Stock", "Type");
        System.out.println("-".repeat(80));
        
        for (Product product : products) {
            System.out.printf("%-15s %-20s $%-9.2f %-10d %-15s%n",
                product.getProductId(),
                product.getName().substring(0, Math.min(19, product.getName().length())),
                product.getPrice(),
                product.getStockQuantity(),
                product.getProductType());
        }
    }
    
    // Helper methods for input
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    private BigDecimal getBigDecimalInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid decimal number.");
            }
        }
    }
}
```

## Reporting and Analytics

### Report Generator
```java
public class ReportGenerator {
    
    public void generateSalesReport(List<Order> orders, LocalDate startDate, LocalDate endDate) {
        List<Order> filteredOrders = orders.stream()
                .filter(order -> {
                    LocalDate orderDate = order.getOrderDate().toLocalDate();
                    return !orderDate.isBefore(startDate) && !orderDate.isAfter(endDate);
                })
                .collect(Collectors.toList());
        
        BigDecimal totalRevenue = filteredOrders.stream()
                .map(Order::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        int totalOrders = filteredOrders.size();
        
        Map<ProductCategory, BigDecimal> revenueByCategory = filteredOrders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                    item -> item.getProduct().getCategory(),
                    Collectors.reducing(BigDecimal.ZERO, 
                        OrderItem::getSubtotal, BigDecimal::add)));
        
        // Generate report
        System.out.println("\n=== Sales Report ===");
        System.out.println("Period: " + startDate + " to " + endDate);
        System.out.println("Total Orders: " + totalOrders);
        System.out.println("Total Revenue: $" + totalRevenue);
        System.out.println("\nRevenue by Category:");
        revenueByCategory.forEach((category, revenue) ->
            System.out.println(category + ": $" + revenue));
    }
    
    public void generateInventoryReport(List<Product> products) {
        System.out.println("\n=== Inventory Report ===");
        System.out.printf("%-15s %-20s %-10s %-15s%n", "ID", "Name", "Stock", "Status");
        System.out.println("-".repeat(70));
        
        for (Product product : products) {
            String status = product.isInStock() ? "In Stock" : "Out of Stock";
            if (product.getStockQuantity() <= 10) {
                status = "Low Stock";
            }
            
            System.out.printf("%-15s %-20s %-10d %-15s%n",
                product.getProductId(),
                product.getName().substring(0, Math.min(19, product.getName().length())),
                product.getStockQuantity(),
                status);
        }
        
        long outOfStock = products.stream().filter(p -> !p.isInStock()).count();
        long lowStock = products.stream().filter(p -> p.getStockQuantity() <= 10 && p.getStockQuantity() > 0).count();
        
        System.out.println("\nSummary:");
        System.out.println("Total Products: " + products.size());
        System.out.println("Out of Stock: " + outOfStock);
        System.out.println("Low Stock: " + lowStock);
    }
    
    public void generateCustomerReport(List<Customer> customers) {
        System.out.println("\n=== Customer Report ===");
        System.out.printf("%-15s %-20s %-15s %-10s %-10s%n", 
            "ID", "Name", "Email", "Orders", "Total Spent");
        System.out.println("-".repeat(80));
        
        for (Customer customer : customers) {
            System.out.printf("%-15s %-20s %-15s %-10d $%-9.2f%n",
                customer.getCustomerId(),
                customer.getFirstName() + " " + customer.getLastName(),
                customer.getEmail(),
                customer.getOrderCount(),
                customer.getTotalSpent());
        }
        
        Map<CustomerTier, Long> customersByTier = customers.stream()
                .collect(Collectors.groupingBy(Customer::getCustomerTier, Collectors.counting()));
        
        System.out.println("\nCustomers by Tier:");
        customersByTier.forEach((tier, count) ->
            System.out.println(tier + ": " + count + " customers"));
    }
}
```

## Exception Handling

### Custom Exceptions
```java
public class EcommerceException extends Exception {
    public EcommerceException(String message) {
        super(message);
    }
    
    public EcommerceException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class ProductNotFoundException extends EcommerceException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

public class InsufficientStockException extends EcommerceException {
    public InsufficientStockException(String message) {
        super(message);
    }
}

public class OrderValidationException extends EcommerceException {
    public OrderValidationException(String message) {
        super(message);
    }
}

public class PaymentFailedException extends EcommerceException {
    public PaymentFailedException(String message) {
        super(message);
    }
}

public class StorageException extends EcommerceException {
    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
```

## Configuration and Utilities

### Application Properties
```properties
# application.properties
app.name=E-commerce Management System
app.version=1.0.0

# Database Configuration
db.url=jdbc:mysql://localhost:3306/ecommerce
db.username=root
db.password=password

# File Storage Configuration
storage.data.dir=data/
storage.backup.dir=backup/

# Email Configuration
email.smtp.host=smtp.gmail.com
email.smtp.port=587
email.username=admin@ecommerce.com
email.password=password

# Business Rules
inventory.low.stock.threshold=10
order.max.items=50
customer.max.orders.per.day=10

# Tax Configuration
tax.rate=0.08
shipping.base.cost=10.00
```

### Utility Classes
```java
public class ValidationUtils {
    
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        
        String phoneRegex = "^\\+?[1-9]\\d{1,14}$";
        return phone.replaceAll("[\\s\\-\\(\\)]", "").matches(phoneRegex);
    }
    
    public static boolean isValidPrice(BigDecimal price) {
        return price != null && price.compareTo(BigDecimal.ZERO) >= 0;
    }
    
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }
}

public class LoggingUtils {
    private static final Logger logger = Logger.getLogger(LoggingUtils.class.getName());
    
    public static void logInfo(String message) {
        logger.info(message);
    }
    
    public static void logError(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
    
    public static void logWarning(String message) {
        logger.warning(message);
    }
}
```

## Testing and Quality Assurance

### Unit Tests
```java
public class InventoryManagerTest {
    private InventoryManager inventoryManager;
    
    @BeforeEach
    void setUp() {
        inventoryManager = new InventoryManager();
    }
    
    @Test
    void testAddProduct() {
        Product product = new Electronics();
        product.setName("Test Product");
        product.setPrice(new BigDecimal("100.00"));
        
        inventoryManager.addProduct(product);
        
        assertTrue(inventoryManager.getAllProducts().contains(product));
    }
    
    @Test
    void testUpdateStock() {
        Product product = new Electronics();
        product.setStockQuantity(10);
        inventoryManager.addProduct(product);
        
        inventoryManager.updateStock(product.getProductId(), -5);
        
        assertEquals(5, product.getStockQuantity());
    }
    
    @Test
    void testInsufficientStockException() {
        Product product = new Electronics();
        product.setStockQuantity(5);
        inventoryManager.addProduct(product);
        
        assertThrows(InsufficientStockException.class, () -> {
            inventoryManager.updateStock(product.getProductId(), -10);
        });
    }
}
```

## Deployment and Documentation

### Main Application Class
```java
public class EcommerceApplication {
    public static void main(String[] args) {
        try {
            // Initialize configuration
            ConfigurationManager config = ConfigurationManager.getInstance();
            
            // Set up logging
            setupLogging();
            
            // Start the application
            EcommerceConsole console = new EcommerceConsole();
            console.start();
            
        } catch (Exception e) {
            System.err.println("Failed to start application: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private static void setupLogging() {
        try {
            LogManager.getLogManager().readConfiguration(
                EcommerceApplication.class.getClassLoader()
                    .getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            System.err.println("Failed to configure logging: " + e.getMessage());
        }
    }
}
```

## Project Features Summary

### Core Features
- ✅ **Product Management**: Add, update, delete, and search products
- ✅ **Inventory Management**: Stock tracking, low stock alerts, inventory reports
- ✅ **Customer Management**: Customer registration, profiles, order history
- ✅ **Order Processing**: Order creation, validation, payment processing
- ✅ **Reporting**: Sales reports, inventory reports, customer analytics

### Advanced Features
- ✅ **Design Patterns**: Factory, Observer, Strategy, Singleton
- ✅ **Exception Handling**: Comprehensive error handling and custom exceptions
- ✅ **Data Persistence**: File-based storage with serialization
- ✅ **Validation**: Input validation and business rule enforcement
- ✅ **Logging**: Application logging and error tracking
- ✅ **Configuration**: External configuration management

### Technical Excellence
- ✅ **SOLID Principles**: Clean, maintainable, and extensible code
- ✅ **Modular Architecture**: Separation of concerns and loose coupling
- ✅ **Type Safety**: Generics and strong typing throughout
- ✅ **Performance**: Efficient data structures and algorithms
- ✅ **Scalability**: Design that supports future enhancements

This E-commerce Management System demonstrates real-world Java development practices and provides a solid foundation for understanding complex system design and implementation.

## Next Steps
After completing this project, students can:
- Add a web-based user interface
- Implement database persistence
- Add REST API endpoints
- Implement user authentication and authorization
- Add real-time notifications
- Implement advanced analytics and machine learning features