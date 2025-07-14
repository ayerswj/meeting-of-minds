# Databases and Storage

## Amazon RDS (Relational Database Service)
### Key Concepts
- Managed relational database for MySQL, PostgreSQL, MariaDB, Oracle, SQL Server.
- Automated backups, patching, and scaling.
- Multi-AZ deployments for high availability.
- Read replicas for scaling reads.

### Use Cases
- Web and mobile applications, analytics, business applications.

### Best Practices
- Enable automated backups and Multi-AZ for production.
- Use security groups to restrict access.
- Regularly apply maintenance updates.

### Example: Create a MySQL RDS Instance (CLI)
```sh
aws rds create-db-instance \
  --db-instance-identifier mydb \
  --db-instance-class db.t2.micro \
  --engine mysql \
  --master-username admin \
  --master-user-password password \
  --allocated-storage 20
```

---

## Amazon DynamoDB
### Key Concepts
- Fully managed NoSQL database.
- Tables, items, and attributes.
- On-demand and provisioned capacity modes.
- Global tables for multi-region replication.

### Use Cases
- Serverless applications, IoT, gaming, real-time analytics.

### Best Practices
- Use on-demand mode for unpredictable workloads.
- Use indexes for efficient queries.
- Enable point-in-time recovery for critical tables.

### Example: Create a Table (CLI)
```sh
aws dynamodb create-table \
  --table-name Music \
  --attribute-definitions AttributeName=Artist,AttributeType=S AttributeName=SongTitle,AttributeType=S \
  --key-schema AttributeName=Artist,KeyType=HASH AttributeName=SongTitle,KeyType=RANGE \
  --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5
```

---

## Amazon EBS (Elastic Block Store)
### Key Concepts
- Block storage for EC2 instances.
- Persistent, high-performance storage.
- Snapshots for backup and disaster recovery.

### Use Cases
- Databases, file systems, boot volumes for EC2.

### Best Practices
- Take regular snapshots for backup.
- Use encrypted volumes for sensitive data.
- Monitor performance metrics.

### Example: Create a Volume (CLI)
```sh
aws ec2 create-volume --availability-zone us-east-1a --size 10 --volume-type gp2
```

---

## Amazon EFS (Elastic File System)
### Key Concepts
- Scalable, elastic file storage for EC2.
- Supports NFS protocol.
- Grows and shrinks automatically as files are added/removed.

### Use Cases
- Shared file storage for web servers, content management, data science.

### Best Practices
- Use security groups and NFS permissions to control access.
- Monitor usage and performance.

### Example: Create a File System (CLI)
```sh
aws efs create-file-system --creation-token my-efs
```

---

**Next:** Automation and deployment on AWS.