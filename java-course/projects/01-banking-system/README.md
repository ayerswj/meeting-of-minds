# Project 1: Banking System

## Project Overview
Build a complete banking system that demonstrates object-oriented programming concepts including classes, inheritance, encapsulation, and polymorphism.

## Learning Objectives
- Apply OOP principles in a real-world scenario
- Practice creating multiple related classes
- Implement inheritance and method overriding
- Use collections to manage data
- Handle user input and display information

## Project Requirements

### Core Features
1. **Account Management**
   - Create different types of accounts (Savings, Checking)
   - Deposit and withdraw money
   - Check account balance
   - Transfer money between accounts

2. **Customer Management**
   - Register new customers
   - Link multiple accounts to customers
   - View customer information

3. **Transaction History**
   - Record all transactions
   - Display transaction history
   - Calculate account statistics

### Account Types

#### Base Account Class
```java
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String ownerName;
    protected List<Transaction> transactions;
    
    // Abstract methods
    public abstract void calculateInterest();
    public abstract double getInterestRate();
}
```

#### Savings Account
- Higher interest rate
- Minimum balance requirement
- Limited withdrawals per month

#### Checking Account
- Lower interest rate
- No minimum balance
- Unlimited transactions

### Transaction Class
```java
public class Transaction {
    private String transactionId;
    private String accountNumber;
    private TransactionType type;
    private double amount;
    private LocalDateTime timestamp;
    private String description;
}
```

## Implementation Steps

### Step 1: Create Base Classes
1. Create `Account` abstract class
2. Create `Transaction` class
3. Create `Customer` class

### Step 2: Implement Account Types
1. Create `SavingsAccount` class
2. Create `CheckingAccount` class
3. Implement specific methods for each account type

### Step 3: Create Banking System
1. Create `BankingSystem` class to manage everything
2. Implement account creation and management
3. Add transaction processing

### Step 4: User Interface
1. Create a simple console-based menu
2. Handle user input
3. Display account information and transactions

## Class Structure

```
BankingSystem/
├── models/
│   ├── Account.java
│   ├── SavingsAccount.java
│   ├── CheckingAccount.java
│   ├── Transaction.java
│   ├── Customer.java
│   └── TransactionType.java
├── services/
│   ├── BankingSystem.java
│   ├── AccountService.java
│   └── TransactionService.java
├── utils/
│   ├── AccountNumberGenerator.java
│   └── ValidationUtils.java
└── Main.java
```

## Example Implementation

### Account.java (Abstract Base Class)
```java
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected String ownerName;
    protected List<Transaction> transactions;
    protected LocalDateTime dateCreated;
    
    public Account(String ownerName) {
        this.ownerName = ownerName;
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
        this.dateCreated = LocalDateTime.now();
    }
    
    public abstract void calculateInterest();
    public abstract double getInterestRate();
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            addTransaction(TransactionType.DEPOSIT, amount, "Deposit");
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
    
    public boolean withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            addTransaction(TransactionType.WITHDRAWAL, amount, "Withdrawal");
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Insufficient funds or invalid amount");
            return false;
        }
    }
    
    protected abstract boolean canWithdraw(double amount);
    
    private void addTransaction(TransactionType type, double amount, String description) {
        Transaction transaction = new Transaction(accountNumber, type, amount, description);
        transactions.add(transaction);
    }
    
    // Getters and setters
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getOwnerName() { return ownerName; }
    public List<Transaction> getTransactions() { return transactions; }
}
```

### SavingsAccount.java
```java
public class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.025; // 2.5%
    private static final double MINIMUM_BALANCE = 100.0;
    private static final int MAX_WITHDRAWALS = 3;
    private int withdrawalsThisMonth;
    
    public SavingsAccount(String ownerName) {
        super(ownerName);
        this.withdrawalsThisMonth = 0;
    }
    
    @Override
    public void calculateInterest() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        addTransaction(TransactionType.INTEREST, interest, "Interest earned");
    }
    
    @Override
    public double getInterestRate() {
        return INTEREST_RATE;
    }
    
    @Override
    protected boolean canWithdraw(double amount) {
        return amount > 0 && 
               amount <= balance && 
               (balance - amount) >= MINIMUM_BALANCE &&
               withdrawalsThisMonth < MAX_WITHDRAWALS;
    }
    
    public void resetMonthlyWithdrawals() {
        withdrawalsThisMonth = 0;
    }
}
```

### BankingSystem.java
```java
public class BankingSystem {
    private Map<String, Customer> customers;
    private Map<String, Account> accounts;
    private AccountService accountService;
    private TransactionService transactionService;
    
    public BankingSystem() {
        this.customers = new HashMap<>();
        this.accounts = new HashMap<>();
        this.accountService = new AccountService();
        this.transactionService = new TransactionService();
    }
    
    public Customer createCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        customers.put(customer.getCustomerId(), customer);
        return customer;
    }
    
    public SavingsAccount createSavingsAccount(String customerId, double initialDeposit) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }
        
        SavingsAccount account = new SavingsAccount(customer.getName());
        if (initialDeposit > 0) {
            account.deposit(initialDeposit);
        }
        
        accounts.put(account.getAccountNumber(), account);
        customer.addAccount(account);
        return account;
    }
    
    public void displayAccountInfo(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Owner: " + account.getOwnerName());
            System.out.println("Balance: $" + account.getBalance());
            System.out.println("Account Type: " + account.getClass().getSimpleName());
        } else {
            System.out.println("Account not found");
        }
    }
    
    public void displayTransactionHistory(String accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Transaction History for Account: " + accountNumber);
            System.out.println("----------------------------------------");
            for (Transaction transaction : account.getTransactions()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Account not found");
        }
    }
}
```

## Testing the System

### Main.java
```java
public class Main {
    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();
        
        // Create a customer
        Customer customer = bank.createCustomer("John Doe", "john@email.com");
        System.out.println("Created customer: " + customer.getName());
        
        // Create accounts
        SavingsAccount savings = bank.createSavingsAccount(customer.getCustomerId(), 1000.0);
        CheckingAccount checking = bank.createCheckingAccount(customer.getCustomerId(), 500.0);
        
        // Perform transactions
        savings.deposit(500.0);
        savings.withdraw(200.0);
        checking.deposit(300.0);
        
        // Display information
        bank.displayAccountInfo(savings.getAccountNumber());
        bank.displayTransactionHistory(savings.getAccountNumber());
    }
}
```

## Advanced Features (Optional)

### 1. Interest Calculation
- Implement monthly interest calculation
- Different rates for different account types
- Compound interest

### 2. Account Transfers
- Transfer money between accounts
- Handle transfer fees
- Validate transfer limits

### 3. Account Statements
- Generate monthly statements
- Export to PDF or text format
- Email statements to customers

### 4. Data Persistence
- Save accounts to files
- Load accounts on startup
- Transaction logging

## Exercises

### Exercise 1: Basic Implementation
Implement the core classes and basic functionality:
- Account creation
- Deposits and withdrawals
- Balance checking

### Exercise 2: Transaction History
Add transaction recording and display:
- Record all transactions
- Display transaction history
- Calculate account statistics

### Exercise 3: Account Types
Implement different account types:
- Savings account with interest
- Checking account with overdraft protection
- Business account with different rules

### Exercise 4: Customer Management
Add customer management features:
- Multiple accounts per customer
- Customer information management
- Account linking

## Key Learning Points
1. **Inheritance**: SavingsAccount and CheckingAccount inherit from Account
2. **Polymorphism**: Different account types behave differently
3. **Encapsulation**: Private fields with public methods
4. **Collections**: Using Lists and Maps to manage data
5. **Exception Handling**: Validating inputs and handling errors
6. **Design Patterns**: Service layer pattern for business logic

## Next Steps
After completing this project, you can:
- Add a graphical user interface
- Implement database storage
- Add more account types
- Create a web-based version
- Add security features

This project provides a solid foundation for understanding OOP concepts and can be extended in many directions based on your interests and learning goals.