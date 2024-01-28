# OAuth2 Authorization Server
This is a simple OAuth2 authorization server written in Spring Boot, capable of generating and validating JWT tokens, client registration, and user signup.

## Prerequisites
This project is created using Java 17 and Spring boot 3.2.1, maven framework is used as build system.

Before you begin, ensure you have met the following requirements:

- **Java:** Version 17 or later. You can download it from [OpenJDK](https://openjdk.java.net/).
- **Spring Boot:** Version 3.2.1. Check the [official Spring Boot website](https://spring.io/projects/spring-boot) for details.
- **Maven:** The project uses Maven as the build system. Install Maven by following the instructions [here](https://maven.apache.org/install.html).

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
   java -jar /path/to/deployment/authorization-server-0.0.1.jar
5. The application will be deployed and accessible on the specified port. Make sure to configure environment-specific settings and secure any sensitive information.

## Features

- **JWT Token Handling:** Generate and validate JWT tokens for secure communication.
- **Client Registration:** Allow applications to register for OAuth2 authorization.
- **User Signup:** Enable user registration for accessing protected resources.

## API Endpoints

Here are some of the key API endpoints provided by the OAuth2 authorization service:
### Custom Endpoints
- **`/api/V1/users/signup`:** Register a new user for accessing protected resources.
- **`/api/V1/clients/register`:** Register a new client application for OAuth2 authorization.

### Standard Endpoints
- **`/oauth2/token`:** Exchange authorization code for access and refresh tokens.
- **`/oauth2/authorize`:** Initiates the OAuth2 authorization process.

Refer to the [API Documentation](./docs/DOCUMENTATION.md) for a complete list of endpoints and their usage.

## License

This project is licensed under the [MIT License](LICENSE.md).