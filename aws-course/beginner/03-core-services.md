# Core AWS Services

## Amazon EC2 (Elastic Compute Cloud)
### Key Concepts
- Instances: Virtual servers for running applications.
- AMI: Pre-configured templates for your instances.
- Instance Types: Different combinations of CPU, memory, storage, and networking.
- Security Groups: Virtual firewalls for your instances.

### Use Cases
- Web hosting, application servers, development/test environments.

### Best Practices
- Use security groups to restrict access.
- Use IAM roles for EC2 to grant permissions securely.
- Regularly patch and update your instances.

### Example: Launching an EC2 Instance (CLI)
```sh
aws ec2 run-instances --image-id ami-0abcdef1234567890 --count 1 --instance-type t2.micro --key-name MyKeyPair --security-group-ids sg-0123456789abcdef0 --subnet-id subnet-6e7f829e
```

---

## Amazon S3 (Simple Storage Service)
### Key Concepts
- Buckets: Containers for storing objects.
- Objects: Files and metadata.
- Storage Classes: Standard, Intelligent-Tiering, Glacier, etc.
- Versioning: Keep multiple versions of an object.

### Use Cases
- Backup and restore, static website hosting, data lakes.

### Best Practices
- Enable versioning for critical data.
- Use bucket policies and IAM for access control.
- Enable server-side encryption for sensitive data.

### Example: Uploading a File to S3 (CLI)
```sh
aws s3 cp myfile.txt s3://my-bucket/
```

---

## IAM (Identity and Access Management)
### Key Concepts
- Users, Groups, Roles, Policies.
- Principle of Least Privilege.
- Multi-Factor Authentication (MFA).

### Use Cases
- Secure access to AWS resources, manage permissions for teams.

### Best Practices
- Use groups to assign permissions.
- Enable MFA for all users.
- Regularly review and audit IAM policies.

### Example: Creating a User (CLI)
```sh
aws iam create-user --user-name newuser
```

---

## AWS CLI (Command Line Interface)
### Key Concepts
- Unified tool to manage AWS services.
- Supports scripting and automation.

### Installation
- [AWS CLI Installation Guide](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

### Example: Listing S3 Buckets
```sh
aws s3 ls
```

### Example: Describing EC2 Instances
```sh
aws ec2 describe-instances
```

---

**Next:** Hands-on labs to practice these services.