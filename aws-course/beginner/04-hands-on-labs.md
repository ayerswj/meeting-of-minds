# Hands-on Labs: Beginner

## Lab 1: Launching an EC2 Instance
1. Log in to the AWS Management Console.
2. Navigate to EC2 > Instances > Launch Instance.
3. Choose an Amazon Machine Image (AMI), e.g., Amazon Linux 2.
4. Select an instance type (t2.micro is free tier eligible).
5. Configure instance details and add storage.
6. Add tags (optional).
7. Configure security group (allow SSH from your IP).
8. Review and launch. Download the key pair.
9. Connect to your instance using SSH.

## Lab 2: Creating and Managing S3 Buckets
1. Go to S3 in the AWS Console.
2. Click 'Create bucket'.
3. Enter a unique bucket name and select a region.
4. Leave default settings or configure as needed.
5. Upload a file to your bucket.
6. Set permissions and test public/private access.

## Lab 3: Setting up IAM Users and Policies
1. Go to IAM in the AWS Console.
2. Create a new user (programmatic access and/or console access).
3. Assign the user to a group with appropriate permissions (e.g., S3FullAccess).
4. Enable MFA for the user.
5. Test logging in as the new user.

---

**Next:** Review additional resources and continue to the Intermediate section.