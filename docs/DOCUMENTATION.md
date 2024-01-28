# OAuth2 Authorization Service

This project implements a basic OAuth2 authorization server using Spring Boot. It provides the following API endpoints for OAuth2 authentication and authorization.

## Endpoints

### 1. User Registration Endpoint

- **Endpoint:** `/api/V1/users/signup`
- **Method:** POST
- **Description:** Register a new user for accessing protected resources.
- **Request Format:**
  ```json
  {
    "username": "user",
    "password": "passwd",
    "roles": ["admin", "user"]
  }
- **Response Format:**
  ```json
  {
    "username": "user",
    "is_active": false,
    "roles": ["admin", "user"]
  }
  
### 2. Client Registration Endpoint

- **Endpoint:** `/api/V1/clients/register`
- **Method:** POST
- **Description:** Register a new client application for OAuth2 authorization. Authorized request with SCOPE : 'client.write' needed.
- **Request Format:**
  ```json
  {
    "client_name": "my-client",
    "authorization_grant_types": ["refresh_token", 
                                  "client_credentials"],
    "client_authentication_methods": ["client_secret_basic"],
    "redirect_uris": ["https://oauth.pstmn.io/v1/callback", 
                      "https://localhost:9000/login"],
    "post_logout_redirect_uris": [],
    "scopes": ["openid", "profile", "read"]
  }
- **Response Format:**
  ```json
  {
    "client_id": "d2b892d9f5664343bccfeaaec8a1fb42",
    "client_secret": "6e18774f41014546a6a747b04426bdd4"
  }

## Error Handling

### 1. Error Response

- **Description:** In the event of any illegal activity, an appropriate response is returned when an error is thrown by the server.
- **Response Format:**
  ```json
  {
    "status": "400 BAD_REQUEST",
    "error": "IllegalValueException",
    "message": "password must be at least 8 characters long.",
    "path": "/api/V1/users/signup",
    "timestamp": "28-01-2024 11:35:15"
  }
