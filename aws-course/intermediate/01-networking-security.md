# Networking and Security

## Amazon VPC (Virtual Private Cloud)
### Key Concepts
- Isolated section of AWS Cloud for launching resources.
- Components: subnets, route tables, internet gateways, NAT gateways, network ACLs.

### Example VPC Architecture (Text Diagram)
```
VPC
├── Public Subnet (EC2, ELB)
│   └── Internet Gateway
└── Private Subnet (RDS, App Servers)
    └── NAT Gateway
```

### Best Practices
- Use multiple Availability Zones for high availability.
- Restrict access with security groups and network ACLs.
- Use private subnets for sensitive resources.

### Example: Create a VPC (CLI)
```sh
aws ec2 create-vpc --cidr-block 10.0.0.0/16
```

---

## Subnets
### Key Concepts
- Public subnets: Direct access to the internet via Internet Gateway.
- Private subnets: No direct internet access; use NAT Gateway for outbound traffic.

### Example: Create a Subnet (CLI)
```sh
aws ec2 create-subnet --vpc-id vpc-12345678 --cidr-block 10.0.1.0/24
```

---

## Security Groups
### Key Concepts
- Virtual firewalls for EC2 instances.
- Control inbound and outbound traffic by rules.

### Best Practices
- Allow only necessary ports (e.g., 22 for SSH, 80/443 for web).
- Use separate security groups for different tiers (web, app, db).

### Example: Create a Security Group (CLI)
```sh
aws ec2 create-security-group --group-name my-sg --description "My security group" --vpc-id vpc-12345678
```

---

## Route 53
### Key Concepts
- Managed DNS service.
- Supports domain registration, DNS routing, health checks.

### Example: Register a Domain (Console)
- Go to Route 53 > Registered domains > Register domain.

### Example: Create a Hosted Zone (CLI)
```sh
aws route53 create-hosted-zone --name example.com --caller-reference 1
```

---

## CloudWatch
### Key Concepts
- Monitoring and observability service.
- Collects logs, metrics, and events from AWS resources.

### Example: View EC2 Metrics (Console)
- Go to CloudWatch > Metrics > EC2.

### Example: Put a Custom Metric (CLI)
```sh
aws cloudwatch put-metric-data --metric-name MyMetric --namespace MyApp --value 1
```

---

## CloudTrail
### Key Concepts
- Tracks user activity and API usage.
- Provides event history for your AWS account.

### Example: Enable CloudTrail (Console)
- Go to CloudTrail > Trails > Create trail.

### Example: Lookup Events (CLI)
```sh
aws cloudtrail lookup-events --max-results 5
```