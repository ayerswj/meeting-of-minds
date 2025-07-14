# Advanced Compute and Networking

## Auto Scaling
### Key Concepts
- Automatically adjusts the number of EC2 instances based on demand.
- Scaling policies: target tracking, step scaling, scheduled scaling.
- Launch configurations and launch templates.

### Use Cases
- Handle variable workloads (e.g., web traffic spikes).
- Cost optimization by scaling in during low demand.

### Best Practices
- Use health checks to replace unhealthy instances.
- Set appropriate scaling thresholds to avoid rapid scaling events.
- Use launch templates for flexibility and new features.

### Example: Create an Auto Scaling Group (CLI)
```sh
aws autoscaling create-auto-scaling-group \
  --auto-scaling-group-name my-asg \
  --launch-template LaunchTemplateName=my-template,Version=1 \
  --min-size 1 --max-size 5 --desired-capacity 2 \
  --vpc-zone-identifier subnet-12345678
```

---

## Elastic Load Balancing (ELB)
### Key Concepts
- Distributes incoming traffic across multiple targets (EC2, containers, IPs).
- Types: Application Load Balancer (ALB), Network Load Balancer (NLB), Classic Load Balancer.
- Listener rules for routing traffic.

### Use Cases
- High availability and fault tolerance for web applications.
- Blue/green deployments and canary releases.

### Best Practices
- Use ALB for HTTP/HTTPS, NLB for TCP/UDP, Classic for legacy apps.
- Enable access logs for monitoring.
- Use health checks to route traffic only to healthy targets.

### Example: Create an Application Load Balancer (CLI)
```sh
aws elbv2 create-load-balancer \
  --name my-alb \
  --subnets subnet-12345678 subnet-87654321 \
  --security-groups sg-12345678
```

---

## AWS Lambda (Serverless)
### Key Concepts
- Run code without provisioning or managing servers.
- Event-driven: triggers from S3, API Gateway, DynamoDB, etc.
- Pay only for compute time consumed.

### Use Cases
- Real-time file processing, automation, backend APIs.
- Scheduled tasks (cron jobs).

### Best Practices
- Keep functions small and single-purpose.
- Use environment variables for configuration.
- Monitor with CloudWatch Logs and Metrics.

### Example: Create a Lambda Function (CLI)
```sh
aws lambda create-function \
  --function-name my-function \
  --runtime python3.9 \
  --role arn:aws:iam::123456789012:role/lambda-role \
  --handler lambda_function.lambda_handler \
  --zip-file fileb://function.zip
```

---

## API Gateway
### Key Concepts
- Create, publish, maintain, monitor, and secure APIs at any scale.
- Supports REST, HTTP, and WebSocket APIs.
- Integrates with Lambda and other AWS services.

### Use Cases
- Serverless REST APIs, WebSocket APIs for real-time apps.
- API management and throttling.

### Best Practices
- Use usage plans and API keys to manage access.
- Enable caching for performance.
- Monitor with CloudWatch metrics and logs.

### Example: Create a REST API (CLI)
```sh
aws apigateway create-rest-api --name 'MyAPI'
```