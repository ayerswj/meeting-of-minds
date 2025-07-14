# Lesson 2: Setting up the Development Environment

## Objectives
- Install the necessary tools to write and run C# code
- Set up Visual Studio or Visual Studio Code
- Verify your environment is ready for C# development

## Step-by-Step Instructions

### 1. Install .NET SDK
- Go to the [.NET download page](https://dotnet.microsoft.com/download)
- Download and install the latest stable .NET SDK for your operating system (Windows, Mac, or Linux)
- Verify installation: Open a terminal or command prompt and run:
  ```sh
  dotnet --version
  ```
  You should see the installed version number.

### 2. Install an Editor
- **Visual Studio (Windows/Mac):**
  - Download from [Visual Studio](https://visualstudio.microsoft.com/)
  - Choose the Community edition (free)
  - During installation, select the ".NET desktop development" workload
- **Visual Studio Code (Windows/Mac/Linux):**
  - Download from [VS Code](https://code.visualstudio.com/)
  - Install the "C#" extension from Microsoft (search for "C#" in the Extensions panel)

### 3. Create Your First Project
- Open a terminal or command prompt
- Run:
  ```sh
  dotnet new console -o HelloWorld
  cd HelloWorld
  dotnet run
  ```
- You should see `Hello World!` printed in the terminal

## Troubleshooting
- If `dotnet` is not recognized, restart your terminal or computer
- Make sure the .NET SDK is added to your PATH
- Check the official [installation guide](https://learn.microsoft.com/en-us/dotnet/core/install/) for more help

## Exercise
- Take a screenshot of your terminal showing the output of `dotnet --version` and `dotnet run`