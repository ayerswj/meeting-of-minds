# Automation and Deployment

## AWS CloudFormation
### Key Concepts
- Infrastructure as Code (IaC): Define AWS resources in YAML or JSON templates.
- Stacks: Collections of resources managed as a single unit.
- Change Sets: Preview changes before applying them.

### Use Cases
- Automate provisioning of complex environments.
- Version control for infrastructure.

### Best Practices
- Modularize templates for reusability.
- Use parameters and mappings for flexibility.
- Store templates in source control (e.g., Git).

### Example: Deploy a Stack (CLI)
```sh
aws cloudformation create-stack --stack-name my-stack --template-body file://template.yaml
```

---

## AWS Elastic Beanstalk
### Key Concepts
- Platform as a Service (PaaS) for deploying web applications.
- Supports multiple languages (Java, Python, Node.js, .NET, etc.).
- Handles provisioning, load balancing, scaling, and monitoring.

### Use Cases
- Rapid deployment of web apps and APIs.
- Managed environments for development, testing, and production.

### Best Practices
- Use environment variables for configuration.
- Monitor health and logs via the Beanstalk console.
- Use rolling updates for zero-downtime deployments.

### Example: Deploy an App (CLI)
```sh
eb init
# Follow prompts to set up your app
eb create my-env
# Deploy changes
eb deploy
```

---

## Introduction to DevOps on AWS
### Key Concepts
- CI/CD: Continuous Integration and Continuous Deployment.
- Key services: CodeCommit (Git repo), CodeBuild (build automation), CodeDeploy (deployment automation), CodePipeline (workflow automation).

### Use Cases
- Automate software delivery pipelines.
- Integrate testing, building, and deployment.

### Best Practices
- Use CodePipeline for end-to-end automation.
- Integrate with CloudFormation for infrastructure automation.
- Monitor pipelines and deployments for failures.

### Example: Create a Pipeline (CLI)
```sh
aws codepipeline create-pipeline --cli-input-json file://pipeline.json
```

---

**Next:** Hands-on labs for intermediate topics.