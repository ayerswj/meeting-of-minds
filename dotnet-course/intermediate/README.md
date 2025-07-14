# Intermediate Level: .NET Course

Welcome to the Intermediate Level of the .NET Course! This section builds on your foundational knowledge and introduces more complex programming concepts and .NET features.

---

## 6. File I/O and Serialization
### Lesson 1: Reading and Writing Files
- **Description:** Learn how to read from and write to files in C#.
- **Key Points:**
  - Using `System.IO.File` methods
  - Reading all lines, writing all lines
  - File paths and relative vs absolute paths
- **Example Code:**
  ```csharp
  string[] lines = File.ReadAllLines("input.txt");
  foreach (var line in lines)
      Console.WriteLine(line);

  File.WriteAllText("output.txt", "Hello, file!");
  ```
- **Tips:**
  - Always close files or use `using` statements to avoid resource leaks.
- **Exercise:**
  - Write a program that reads a file and prints each line with its line number.
  - Write a program that appends a line to a file.
  - Try reading a file that does not exist and handle the exception.

### Lesson 2: JSON Serialization
- **Description:** Convert objects to and from JSON using `System.Text.Json`.
- **Key Points:**
  - Serializing objects: `JsonSerializer.Serialize(obj)`
  - Deserializing objects: `JsonSerializer.Deserialize<T>(json)`
  - Customizing serialization with attributes
- **Example Code:**
  ```csharp
  using System.Text.Json;

  var person = new { Name = "Alice", Age = 30 };
  string json = JsonSerializer.Serialize(person);
  Console.WriteLine(json);

  var deserialized = JsonSerializer.Deserialize<Dictionary<string, object>>(json);
  Console.WriteLine(deserialized["Name"]);
  ```
- **Tips:**
  - Use `JsonSerializerOptions` for formatting and property naming policies.
- **Exercise:**
  - Create a class `Book` and serialize a list of books to JSON.
  - Read a JSON string and deserialize it into a C# object.
  - Try serializing an object with a nested list property.

### Lesson 3: XML Serialization
- **Description:** Work with XML data using `System.Xml.Serialization`.
- **Key Points:**
  - Serializing and deserializing objects to/from XML
  - Using `XmlSerializer`
  - XML attributes for customizing output
- **Example Code:**
  ```csharp
  using System.Xml.Serialization;
  using System.IO;

  [Serializable]
  public class Product
  {
      public string Name { get; set; }
      public double Price { get; set; }
  }

  var product = new Product { Name = "Pen", Price = 1.99 };
  var serializer = new XmlSerializer(typeof(Product));
  using (var stream = new StringWriter())
  {
      serializer.Serialize(stream, product);
      string xml = stream.ToString();
      Console.WriteLine(xml);
  }
  ```
- **Tips:**
  - XML is useful for interoperability with older systems.
- **Exercise:**
  - Create a class `Student` and serialize/deserialize it to/from XML.
  - Add an attribute to a property and observe the XML output.

---

## 7. Unit Testing and Debugging
### Lesson 1: Writing Unit Tests with xUnit/NUnit
- **Description:** Learn how to write and run unit tests in .NET.
- **Key Points:**
  - Test frameworks: xUnit, NUnit
  - Writing test methods and using assertions
  - Test project structure
- **Example Code:**
  ```csharp
  using Xunit;

  public class MathTests
  {
      [Fact]
      public void Add_ReturnsSum()
      {
          Assert.Equal(4, 2 + 2);
      }
  }
  ```
- **Tips:**
  - Use descriptive test method names.
- **Exercise:**
  - Create a test project and write tests for a simple calculator class.
  - Write a test that checks for an exception.

### Lesson 2: Debugging Techniques
- **Description:** Use debugging tools to find and fix bugs in your code.
- **Key Points:**
  - Setting breakpoints
  - Stepping through code
  - Inspecting variables
- **Tips:**
  - Use the debugger in Visual Studio or VS Code for a visual experience.
- **Exercise:**
  - Set a breakpoint in your code and step through it to watch variable values change.
  - Use the debugger to find and fix a bug in a sample program.

---

## 8. Building Console Applications
### Lesson 1: Command-line Arguments
- **Description:** Accept and process command-line arguments in your applications.
- **Key Points:**
  - Accessing arguments via `string[] args` in `Main`
  - Parsing and validating input
- **Example Code:**
  ```csharp
  class Program
  {
      static void Main(string[] args)
      {
          if (args.Length > 0)
              Console.WriteLine($"First argument: {args[0]}");
          else
              Console.WriteLine("No arguments provided.");
      }
  }
  ```
- **Exercise:**
  - Write a program that takes a filename as an argument and prints its contents.
  - Add error handling for missing or invalid arguments.

### Lesson 2: Structuring Larger Projects
- **Description:** Organize code in larger console applications.
- **Key Points:**
  - Using multiple files and classes
  - Project structure best practices
- **Tips:**
  - Use folders to organize related classes.
- **Exercise:**
  - Refactor a single-file console app into multiple files and classes.

---

## 9. Introduction to .NET Libraries and NuGet
### Lesson 1: Using and Creating NuGet Packages
- **Description:** Use third-party libraries and create your own packages.
- **Key Points:**
  - Installing packages with `dotnet add package`
  - Creating and publishing a NuGet package
- **Tips:**
  - Explore [nuget.org](https://www.nuget.org/) for popular packages.
- **Exercise:**
  - Add a JSON library to your project using NuGet.
  - Create a simple library and package it as a NuGet package (local only).

### Lesson 2: Exploring Popular .NET Libraries
- **Description:** Discover and use popular libraries in the .NET ecosystem.
- **Key Points:**
  - Logging, configuration, HTTP clients, etc.
- **Exercise:**
  - Use `Serilog` for logging in a console app.
  - Use `HttpClient` to fetch data from a public API.

---

## 10. Asynchronous Programming
### Lesson 1: async/await
- **Description:** Write asynchronous code using async/await.
- **Key Points:**
  - Task-based asynchronous pattern
  - Awaiting tasks
- **Example Code:**
  ```csharp
  async Task DownloadAsync()
  {
      using var client = new HttpClient();
      string data = await client.GetStringAsync("https://example.com");
      Console.WriteLine(data);
  }
  ```
- **Tips:**
  - Use async methods for I/O-bound operations.
- **Exercise:**
  - Write an async method that fetches data from a URL and prints it.

### Lesson 2: Tasks and Parallelism
- **Description:** Run code in parallel using tasks.
- **Key Points:**
  - Creating and running tasks
  - Parallel loops
- **Example Code:**
  ```csharp
  Task t = Task.Run(() => Console.WriteLine("Running in a task!"));
  t.Wait();
  ```
- **Exercise:**
  - Write a program that runs two tasks in parallel and waits for both to finish.

---

Congratulations! You have completed the Intermediate Level. Move on to the Advanced Level for desktop, web, and database programming.