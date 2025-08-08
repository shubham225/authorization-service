# OAuth 2.0 Authorization Server – Spring Boot JWT Implementation

[![Java](https://img.shields.io/badge/Java-17+-blue.svg?logo=java)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen.svg?logo=springboot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Build-Maven-orange.svg?logo=apache-maven)](https://maven.apache.org/)
[![Database](https://img.shields.io/badge/Database-MySQL%20%7C%20MariaDB-blue.svg?logo=mysql)](https://www.mysql.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE.md)

A **secure OAuth2 Authorization Server** built with **Spring Boot**, supporting multiple grant types, JWT-based token management, client registration, and user management. Perfect for securing your microservices or API gateway.


## 📋 Features

✅ **OAuth2 Framework** – Supports Authorization Code, Implicit, Client Credentials, and more.  
✅ **JWT Token Management** – Secure access & refresh tokens with encryption.  
✅ **Custom Scopes & RBAC** – Fine-grained control over API access.  
✅ **Client Registration API** – Fully functional endpoints for client management.  
✅ **User Management API** – Secure CRUD operations on user accounts.  
✅ **Admin Dashboards** – Web-based UI for managing clients and users.  
✅ **OpenAPI Documentation** – Easy API exploration via Swagger UI.


## ⚙️ Prerequisites

Before running this project, ensure you have:

- **Java 17+** → [Download OpenJDK](https://openjdk.java.net/)  
- **Spring Boot 3.2.1** → [Official Website](https://spring.io/projects/spring-boot)  
- **Maven** → [Install Maven](https://maven.apache.org/install.html)  
- **MySQL/MariaDB** → [MySQL](https://dev.mysql.com/downloads/) | [MariaDB](https://mariadb.org/download/)  
- **OpenSSL** → [Download](https://www.openssl.org/) for certificate generation

## 🚀 Getting Started

Follow these steps to run the Authorization Server locally.

### 1️⃣ Clone the Repository
   ```bash
   git clone https://github.com/shubham225/authorization-service.git
   ```
### 2️⃣ Build the Project
```bash
mvn clean install
```
This will download dependencies, compile the project, run tests, and package it into a JAR file.

### 3️⃣ Run the Application
```bash
mvn spring-boot:run
```
The server will start on **http://localhost:9000**.

- **Default Database:** In-memory H2 (no configuration needed)  
- **Optional:** Use MySQL/MariaDB by activating the profile:  
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

### 4️⃣ Access Admin Dashboard
Login with default credentials:  
```text
Username: admin
Password: admin
```
⚠️ **Change these credentials immediately in production.**

### 5️⃣ API Documentation
Once running, explore APIs via Swagger UI:  
```text
http://localhost:9000/swagger-ui/index.html
```

## 🌍 Deployment

**Build JAR:**
```bash
mvn clean install
```

**Deploy to Server:**
```bash
scp target/authorization-server-0.0.1.jar user@your-server-ip:/path/to/deployment/
ssh user@your-server-ip
java -jar /path/to/deployment/authorization-server-0.0.1.jar -Dspring.profiles.active=mysql
```

💡 **Tip:** Use `-Dspring.profiles.active=mysql` for MySQL/MariaDB; omit for H2 in-memory mode.

---

## 🔑 Default Credentials
```text
Username: admin
Password: admin
```
⚠️ **Change these credentials immediately after deployment!**

---

## 🔧 Environment Variables

| Variable              | Description                                                | Example                                      |
|----------------------|------------------------------------------------------------|----------------------------------------------|
| `AUTH_DATASOURCE_URL` | JDBC URL to your database                                  | `jdbc:mariadb://localhost:3306/auth_db`     |
| `AUTH_DB_USER`        | Database username                                          | `auth_user`                                 |
| `AUTH_DB_PASSWORD`    | Database password                                          | `strongpassword`                            |
| `JWT_KEY_ID` *(opt)*  | Custom JWT Key ID                                          | `my-key-id`                                 |

---

## 📡 API Endpoints

### 🔹 Registration
- **POST** `/api/V1/users/` – Register a new user  
- **POST** `/api/V1/clients/register` – Register a new OAuth2 client  

### 🔹 Discovery
- **GET** `/.well-known/oauth-authorization-server` – OAuth2 metadata  
- **GET** `/.well-known/openid-configuration` – OpenID Connect config  

### 🔹 Authorization & Token
- **GET** `/oauth2/authorize` – Authorization endpoint  
- **POST** `/oauth2/token` – Token issuance  
- **GET** `/oauth2/jwks` – JWKS public keys  
- **POST** `/oauth2/introspect` – Token introspection  
- **POST** `/oauth2/revoke` – Token revocation  

### 🔹 Other
- **GET** `/actuator/health` – Health check  
- **GET** `/swagger-ui/index.html` – Swagger documentation  

📖 Full docs → [API Documentation](./docs/DOCUMENTATION.md)  

---

## 🔐 Generating RSA Certificates

```bash
cd src/main/resources/certs/
openssl genrsa -out keypair.pem 2048
openssl rsa -in keypair.pem -pubout -out public.pem
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
```
> Keep only `private.pem` and `public.pem`.

---

## 📜 License
This project is licensed under the **MIT License** → [View License](LICENSE.md)