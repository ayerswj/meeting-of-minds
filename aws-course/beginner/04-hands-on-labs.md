# Hands-on Labs: Beginner

## Lab 1: Launching an EC2 Instance
**Learning Objectives:**
- Understand how to launch and connect to an EC2 instance.
- Learn basic security group configuration.

**Steps (Console):**
1. Log in to the AWS Management Console.
2. Navigate to EC2 > Instances > Launch Instance.
3. Choose an Amazon Machine Image (AMI), e.g., Amazon Linux 2.
4. Select an instance type (t2.micro is free tier eligible).
5. Configure instance details and add storage.
6. Add tags (optional).
7. Configure security group (allow SSH from your IP).
8. Review and launch. Download the key pair.
9. Connect to your instance using SSH:
   ```sh
   ssh -i /path/to/key.pem ec2-user@<public-ip>
   ```

**Steps (CLI):**
1. Configure the AWS CLI with your credentials:
   ```sh
   aws configure
   ```
2. Launch an instance:
   ```sh
   aws ec2 run-instances --image-id ami-0abcdef1234567890 --count 1 --instance-type t2.micro --key-name MyKeyPair --security-group-ids sg-0123456789abcdef0 --subnet-id subnet-6e7f829e
   ```

**Troubleshooting:**
- Ensure your security group allows SSH (port 22) from your IP.
- Check that your key pair permissions are set to 400.

---

## Lab 2: Creating and Managing S3 Buckets
**Learning Objectives:**
- Create, upload, and manage objects in S3.
- Set permissions and test access.

**Steps (Console):**
1. Go to S3 in the AWS Console.
2. Click 'Create bucket'.
3. Enter a unique bucket name and select a region.
4. Leave default settings or configure as needed.
5. Upload a file to your bucket.
6. Set permissions and test public/private access.

**Steps (CLI):**
1. Create a bucket:
   ```sh
   aws s3 mb s3://my-bucket
   ```
2. Upload a file:
   ```sh
   aws s3 cp myfile.txt s3://my-bucket/
   ```

**Troubleshooting:**
- Bucket names must be globally unique.
- Check permissions if you cannot access the file.

---

## Lab 3: Setting up IAM Users and Policies
**Learning Objectives:**
- Create IAM users, groups, and policies.
- Enable MFA and test user access.

**Steps (Console):**
1. Go to IAM in the AWS Console.
2. Create a new user (programmatic access and/or console access).
3. Assign the user to a group with appropriate permissions (e.g., S3FullAccess).
4. Enable MFA for the user.
5. Test logging in as the new user.

**Steps (CLI):**
1. Create a user:
   ```sh
   aws iam create-user --user-name newuser
   ```
2. Attach a policy:
   ```sh
   aws iam attach-user-policy --user-name newuser --policy-arn arn:aws:iam::aws:policy/AmazonS3FullAccess
   ```

**Troubleshooting:**
- Ensure you have admin permissions to create users.
- Use the correct ARN for policies.