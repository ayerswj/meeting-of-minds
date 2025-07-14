# Advanced Security and Identity

## Advanced IAM
### Key Concepts
- IAM Roles: Grant permissions to AWS services and users.
- Cross-account access: Use roles to allow access between AWS accounts.
- Permission boundaries: Set maximum permissions for roles/users.
- Policy conditions: Add context (e.g., IP, time) to permissions.
- IAM Access Analyzer: Identify resources shared outside your account.

### Use Cases
- Granting EC2 instances access to S3 buckets.
- Delegating access to third-party vendors.
- Restricting actions based on IP or time.

### Best Practices
- Use roles instead of long-term access keys.
- Regularly review and audit IAM policies.
- Enable IAM Access Analyzer for visibility.

### Example: Create a Role for EC2 (CLI)
```sh
aws iam create-role --role-name EC2S3Access --assume-role-policy-document file://trust-policy.json
```

---

## AWS Key Management Service (KMS)
### Key Concepts
- Create and manage cryptographic keys (CMKs).
- Integrated with S3, EBS, RDS, Lambda, and more.
- Key policies and grants for fine-grained access control.

### Use Cases
- Encrypting data at rest in S3, EBS, RDS.
- Managing keys for server-side encryption.
- Auditing key usage with CloudTrail.

### Best Practices
- Enable automatic key rotation.
- Use separate keys for different applications/environments.
- Limit key usage permissions to least privilege.

### Example: Create a KMS Key (CLI)
```sh
aws kms create-key --description "MyApp Key"
```

---

## Amazon Cognito
### Key Concepts
- User pools: User directory for sign-up/sign-in.
- Identity pools: Federated identities for AWS access.
- Supports social (Google, Facebook) and SAML providers.

### Use Cases
- Add authentication to web/mobile apps.
- Enable single sign-on (SSO) for enterprise apps.
- Secure APIs with user authentication.

### Best Practices
- Enable multi-factor authentication (MFA).
- Use pre-sign-up and post-authentication triggers for custom logic.
- Secure user data with encryption and strong password policies.

### Example: Create a User Pool (CLI)
```sh
aws cognito-idp create-user-pool --pool-name MyUserPool
```

---

**Next:** Big data and analytics on AWS.