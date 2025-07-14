# Advanced Level: .NET Course

Welcome to the Advanced Level of the .NET Course! This section covers desktop and web development, databases, security, and deployment. By the end, you'll be able to build robust, production-ready applications.

---

## 11. Building Desktop Applications
### Lesson 1: Windows Forms
- **Description:** Create classic desktop applications using Windows Forms.
- **Key Points:**
  - Designing forms visually
  - Handling events (button clicks, etc.)
  - Adding controls (buttons, textboxes, etc.)
- **Example Code:**
  ```csharp
  using System.Windows.Forms;

  public class MyForm : Form
  {
      public MyForm()
      {
          var button = new Button { Text = "Click Me" };
          button.Click += (s, e) => MessageBox.Show("Hello!");
          Controls.Add(button);
      }
  }
  // Usage:
  Application.Run(new MyForm());
  ```
- **Tips:**
  - Use the Visual Studio designer for rapid UI development.
- **Exercise:**
  - Create a form with a textbox and a button. When the button is clicked, display the textbox content in a message box.

### Lesson 2: WPF (Windows Presentation Foundation)
- **Description:** Build modern, flexible desktop UIs with XAML and WPF.
- **Key Points:**
  - XAML for UI layout
  - Data binding
  - Styles and resources
- **Example Code:**
  ```xml
  <Window x:Class="WpfApp.MainWindow"
          xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
          xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"
          Title="Hello WPF" Height="200" Width="300">
      <StackPanel>
          <TextBox Name="inputBox"/>
          <Button Content="Show" Click="Button_Click"/>
      </StackPanel>
  </Window>
  ```
- **Tips:**
  - Use MVVM for scalable WPF apps.
- **Exercise:**
  - Create a WPF app with a list of items and a button to add new items.

### Lesson 3: MVVM Pattern
- **Description:** Structure WPF apps using the Model-View-ViewModel pattern.
- **Key Points:**
  - Separation of concerns
  - Data binding to ViewModel
  - Commands
- **Exercise:**
  - Refactor a WPF app to use MVVM. Implement a ViewModel with observable properties.

---

## 12. Web Development with ASP.NET Core
### Lesson 1: MVC Pattern
- **Description:** Build web apps using the Model-View-Controller pattern.
- **Key Points:**
  - Controllers, Views, and Models
  - Routing
  - View rendering with Razor
- **Example Code:**
  ```csharp
  public class HomeController : Controller
  {
      public IActionResult Index()
      {
          return View();
      }
  }
  ```
- **Tips:**
  - Use scaffolding to quickly generate controllers and views.
- **Exercise:**
  - Create a simple MVC app with a form that submits data to a controller.

### Lesson 2: Razor Pages
- **Description:** Simplify web development with page-focused programming.
- **Key Points:**
  - PageModel classes
  - Binding form data
- **Exercise:**
  - Build a Razor Pages app with a contact form.

### Lesson 3: REST APIs
- **Description:** Create RESTful APIs with ASP.NET Core.
- **Key Points:**
  - Routing and controllers
  - Returning JSON
  - Attribute routing
- **Example Code:**
  ```csharp
  [ApiController]
  [Route("api/[controller]")]
  public class ProductsController : ControllerBase
  {
      [HttpGet]
      public IEnumerable<Product> Get() => ...;
  }
  ```
- **Tips:**
  - Use Swagger for API documentation.
- **Exercise:**
  - Create an API that returns a list of products as JSON.

### Lesson 4: Middleware and Dependency Injection
- **Description:** Use middleware and DI for cross-cutting concerns and loose coupling.
- **Key Points:**
  - Writing custom middleware
  - Registering and injecting services
- **Exercise:**
  - Add logging middleware to an ASP.NET Core app.
  - Register a custom service and inject it into a controller.

---

## 13. Entity Framework Core
### Lesson 1: ORM Concepts
- **Description:** Understand Object-Relational Mapping and EF Core basics.
- **Key Points:**
  - DbContext and DbSet
  - Mapping classes to tables
- **Exercise:**
  - Create a simple model and context, and save data to a SQLite database.

### Lesson 2: Code-first and Database-first Approaches
- **Description:** Learn the two main ways to use EF Core.
- **Key Points:**
  - Code-first: define models in code
  - Database-first: scaffold from an existing database
- **Exercise:**
  - Scaffold a context from an existing database.
  - Use migrations to update the schema.

### Lesson 3: Migrations and Data Seeding
- **Description:** Manage schema changes and seed initial data.
- **Key Points:**
  - Creating and applying migrations
  - Seeding data in OnModelCreating
- **Exercise:**
  - Add a new property to a model and create a migration.
  - Seed initial data for a table.

---

## 14. Security in .NET Applications
### Lesson 1: Authentication and Authorization
- **Description:** Secure your apps with authentication and authorization.
- **Key Points:**
  - Identity framework
  - Role-based and policy-based authorization
- **Exercise:**
  - Add login and registration to an ASP.NET Core app.
  - Restrict access to a controller by role.

### Lesson 2: Secure Coding Practices
- **Description:** Write secure code to prevent vulnerabilities.
- **Key Points:**
  - Input validation
  - Protecting sensitive data
  - Avoiding common pitfalls (SQL injection, XSS)
- **Tips:**
  - Use parameterized queries and built-in validation.
- **Exercise:**
  - Identify and fix a security flaw in a sample app.

---

## 15. Deployment and Cloud Integration
### Lesson 1: Publishing .NET Apps
- **Description:** Prepare and publish your .NET applications.
- **Key Points:**
  - Self-contained vs framework-dependent deployments
  - Publishing for different platforms
- **Exercise:**
  - Publish a console or web app for Windows and Linux.

### Lesson 2: Deploying to Azure/AWS
- **Description:** Deploy .NET apps to the cloud.
- **Key Points:**
  - Azure App Service, AWS Elastic Beanstalk
  - Environment variables and configuration
- **Tips:**
  - Use cloud provider documentation for step-by-step guides.
- **Exercise:**
  - Deploy a web app to Azure or AWS and verify it runs.

### Lesson 3: CI/CD Basics
- **Description:** Automate builds and deployments with CI/CD.
- **Key Points:**
  - GitHub Actions, Azure DevOps, GitLab CI
  - Build, test, and deploy pipelines
- **Exercise:**
  - Set up a GitHub Actions workflow to build and test a .NET project.

---

Congratulations! You have completed the Advanced Level. Move on to the Expert Level for microservices, performance, and cross-platform development.