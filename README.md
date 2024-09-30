# OAuth2 Authorization Server
This is a simple OAuth2 authorization server written in Spring Boot, capable of generating and validating JWT tokens, client registration, and user signup.

## Prerequisites
This project is created using Java 17 and Spring boot 3.2.1, maven framework is used as build system.

Before you begin, ensure you have met the following requirements:

- **Java:** Version 17 or later. You can download it from [OpenJDK](https://openjdk.java.net/).
- **Spring Boot:** Version 3.2.1. Check the [official Spring Boot website](https://spring.io/projects/spring-boot) for details.
- **Maven:** The project uses Maven as the build system. Install Maven by following the instructions [here](https://maven.apache.org/install.html).
- **Database**: Set up a MySQL or MariaDB database server. You can install MySQL or MariaDB server locally on your development machine or use a cloud-based database service. You can download MySQL from the [official MySQL website](https://dev.mysql.com/downloads/) or MariaDB from the [official MariaDB website](https://mariadb.org/download/).

## Features

1. **OAuth2 Framework**: Supports multiple grant types (Authorization Code, Implicit, Client Credentials, etc.) with secure token issuance and validation.
2. **JWT Token Management**: Securely handles access and refresh tokens with encryption.
3. **Client Scopes & Roles**: Customizable client scopes and role-based access control (RBAC).
4. **Client Registration API**: Endpoints for registering, updating, and managing OAuth clients.
5. **User Management API**: Endpoints for creating, updating, and managing user accounts securely.
6. **Client Registration UI**: Easy-to-use interface for registering and managing OAuth clients with real-time validation.
7. **User Management Dashboard**: Interface for creating, updating, and managing users, with role-based permissions.

## Getting Started

To get a local copy up and running, follow these simple steps:

1. Clone the repository.
   ```bash
   git clone https://github.com/shubham225/authorization-service.git
2. Navigate to the project directory.
    ```bash
   cd authorization-service
3. Build the project using Maven.
    ```bash
   mvn clean install
4. Run the application.
    ```bash
   mvn spring-boot:run
5. The application will now be accessible at http://localhost:9000. Make sure to configure any additional settings or credentials according to your specific use case.

## Deployment

Follow these steps to deploy the application in a production environment:

1. Build the JAR file:
   ```bash
   mvn clean install
2. Copy the JAR file to the deployment server:
    ```bash
   scp target/authorization-server-0.0.1.jar user@your-server-ip:/path/to/deployment/
3. SSH into the deployment server:
    ```bash
   ssh user@your-server-ip
4. Run the application:
    ```bash
   java -jar <path-to-deployment>/authorization-server-0.0.1.jar -Dspring.profiles.active=mysql
   ```
   here option `-Dspring.profiles.active=mysql` is optional if not given then application will by default start with in memory H2 Database.

5. The application will be deployed and accessible on the specified port. Make sure to configure environment-specific settings and secure any sensitive information.

**_Please Note: Initial credentials for admin will be ```U: admin P: admin``` this needs to be changed after deployment._**

## Environment Variables

To properly configure and run this project, you will need to set up the following environment variables (database env variables not needed to set up if application is executed with profile is H2):

- **AUTH_DATASOURCE_URL**: This variable should be set to the URL of your server datasource. For example, if you're using a MySQL/MariaDB database for service, the URL might look like `jdbc:mariadb://localhost:3306/your_database_name`.

- **AUTH_DB_USER**: Set this variable to the username used to access your database.

- **AUTH_DB_PASSWORD**: Set this variable to the password used to access your database.

- **JWT_KEY_ID [Optional]**: Set this variable if wanted custom JWT Key ID (Optional Env. Variable).

Make sure to set these environment variables either directly in your development environment or using a configuration file such as `application.properties` or `application.yml` for local development. Additionally, when deploying your Spring Boot application, you can configure these variables through your deployment environment settings.

## API Endpoints

Here are some of the key API endpoints provided by the OAuth2 authorization service:
### Registration Endpoints
- **`POST /api/V1/users/`:** Register a new user for accessing protected resources.
- **`POST /api/V1/clients/register`:** Register a new client application for OAuth2 authorization.

### Discovery Endpoints
- **`GET /.well-known/oauth-authorization-server`:** Provides information about the OAuth2 Authorization Server, including its capabilities and supported endpoints.
- **`GET /.well-known/openid-configuration`:** Publishes configuration information, including endpoints and supported features, for clients using OpenID Connect.

### Authorization Endpoints
- **`GET /oauth2/authorize`:** Authorization endpoint for initiating the OAuth2 authorization process.
- **`POST /oauth2/token`:** Token endpoint for obtaining OAuth2 access tokens and refresh tokens.
- **`GET /oauth2/jwks`:** JSON Web Key Set (JWKS) endpoint providing public keys for validating JWTs issued by the Authorization Server.
- **`POST /oauth2/introspect`:** Token introspection endpoint for checking the validity and details of an OAuth2 token.
- **`POST /oauth2/revoke`:** Token revocation endpoint for revoking an OAuth2 token.

### Other Endpoints
- **`GET /actuator/health`:** Exposes the health status of the application
- **`GET /actuator/info`:** Provides general information about the application.
- **`GET /swagger-ui/index.html`:** Swagger UI endpoint for API Documentation

Refer to the [API Documentation](./docs/DOCUMENTATION.md) for a complete list of endpoints and their usage. 

## Generating RSA Certificates
Before getting started, ensure you have OpenSSL installed on your system. If not, you can download and install it from [OpenSSL website](https://www.openssl.org/).
1. Navigate to the certs directory :
   ```bash
   cd src/main/resources/certs/
2. Run the following command to generate a 2048-bit RSA private key:
   ```bash
   openssl genrsa -out keypair.pem 2048
3. Next, extract the public key from the private key generated in the previous step using the following command:
    ```bash
   openssl rsa -in keypair.pem -pubout -out public.pem

4. For compatibility and ease of use, convert the private key to PKCS#8 format using the following command:
    ```bash
   openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem

5. Above commands will create three files in the `certs` directory: `keypair.pem`, `public.pem`, and `private.pem`. We don't need `keypair.pem`; this file can be deleted. The other two files will serve as the private and public keys for signing and validating JWT

## License

This project is licensed under the MIT License - see the [MIT License](LICENSE.md) file for details.