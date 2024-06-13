# OAuth2 Authorization Service

This project implements a basic OAuth2 authorization server using Spring Boot. It provides the following API endpoints for OAuth2 authentication and authorization.

## Endpoints

### 1. User Registration Endpoint

- **Endpoint:** `/api/V1/users/signup`
- **Method:** POST
- **Description:** Register a new user for accessing protected resources.
- **Request:**
  ```json
  {
    "username": "user",
    "password": "passwd",
    "roles": ["admin", "user"]
  }
- **Response:**
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
- **Request:**
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
- **Response:**
  ```json
  {
    "client_id": "d2b892d9f5664343bccfeaaec8a1fb42",
    "client_secret": "6e18774f41014546a6a747b04426bdd4"
  }

### 3. Authorize Endpoint
- **Endpoint:** `/oauth2/authorize`
- **Method:** GET
- **Description:** Authorization endpoint for initiating the OAuth2 authorization process.
This endpoint will redirect to login page and after successful login attempt it will redirect to `redirect_url` 
mentioned in the query of the request with `code` as query parameter. this authorization code can be used in 
/token request to obtain jwt token for data access. 
- **Request:**
  ```bash
  curl --location 'http://<auth-server-url>/oauth2/authorize?response_type=code&client_id=my-client&scope=openid%20profile%20read&redirect_uri=https%3A%2F%2Foauth.pstmn.io%2Fv1%2Fcallback'

- **Response:** User will be redirected to `redirect_url` with `code` as query parameter, url will be as follows:
  ```
  https://oauth.pstmn.io/v1/callback?code=THNuziDTHk1_q9zOG0t0_cHeuMRkAiZcEaYro9LIvkJzvsV7Pkx0rmtytN6_2Z4BQOJQz1kZ8Rx5N1Gw__vjlUuJqj2pjyfkPRa_11IwkMjMPjWH_8HPk0oO8cXVS4ut
  ```
  
### 4. Generate Token Endpoint
- **Endpoint:** `/oauth2/token`
- **Method:** POST
- **Description:** Token endpoint for obtaining OAuth2 access tokens and refresh tokens.
- **Request (grant_type = client_credentials):**
  ```bash
  curl --location 'http://<auth-server-url>/oauth2/token' \
  --header 'Authorization: Basic <client-creds-in-base64>' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data-urlencode 'grant_type=client_credentials' \
  --data-urlencode 'scope=openid profile read'

- **Response (grant_type = client_credentials):**
  ```json
  {
    "access_token": "eyJraWQiOiI3MGQzZjhhMS04ZDZlLTQyYzItODJjZS02NTY5MWE4OGY3ZTUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJteS1jbGllbnQiLCJhdWQiOiJteS1jbGllbnQiLCJuYmYiOjE3MTgwMzUwNzcsInNjb3BlIjpbInJlYWQiLCJvcGVuaWQiLCJwcm9maWxlIl0sImlzcyI6Imh0dHA6Ly9sb2NhbGhvc3Q6OTAwMCIsImV4cCI6MTcxODAzNTM3NywiaWF0IjoxNzE4MDM1MDc3LCJqdGkiOiJiZDMzNmFkNy0wYWFkLTQ2ZjYtYjQwMi02ZDVhOWMxNGViZDgifQ.kCLsaiscuFSbCAn239sioc3JbtJOOPzASsD6rx9T9UdL0pZQX-h7LeM-7a5Ds2JMbAlQ0M9dyHtHtzPhcRbtgcshiz3X6SnCPoIT_Me_CIuo0pS8boGTaucbRRbjrcxhQc9Jv-x7HRKbjpBiZ9HSReLIpYSFBXUno1VJBPF4UC7bsfKRh4rVA59bLsPmiUQtR42S41Op1iegckCY9QZCANn6lErel2Ns5SNxtLCc77OshAs6ESy8ZRwZTsZlsgWKuYPZLBNspIbuEbwvj8W4eTR_COcBgJSUbF3ct_FzSWCBZv0oFywtFDxogB8-mgc6mpmcvb659Wuwmsaux4Fi8Q",
    "scope": "read openid profile",
    "token_type": "Bearer",
    "expires_in": 299
  }

- **Request (grant_type = authorization_code):**
  ```bash
  curl --location 'http://<auth-server-url>/oauth2/token' \
  --header 'Authorization: Basic <client-creds-in-base64>' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data-urlencode 'grant_type=authorization_code' \
  --data-urlencode 'code=<code-generated-from-authorize-endpoint>' \
  --data-urlencode 'redirect_uri=<redirect-url-to-redirect-to>'

- **Response (grant_type = authorization_code):**
  ```json
  {
    "access_token": "eyJraWQiOiI3MGQzZjhhMS04ZDZlLTQyYzItODJjZS02NTY5MWE4OGY3ZTUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaHViaGFtIiwiYXVkIjoibXktY2xpZW50IiwibmJmIjoxNzE4MDM2MzUzLCJzY29wZSI6WyJyZWFkIiwib3BlbmlkIiwicHJvZmlsZSJdLCJyb2xlcyI6WyJhZG1pbiIsInVzZXIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAwIiwiZXhwIjoxNzE4MDM2NjUzLCJpYXQiOjE3MTgwMzYzNTMsImp0aSI6IjliYWVlN2ViLWIxNTctNDFjYi05ZmI0LWIyMzc2MzgyYzgyMCJ9.JHtlE8_hCwZ4WSpyVler12ij1qTaYawZfYFfW0HzKOH94OnPOtBxNNLd6wvJUUYcc96b2mRP4-3gFs5NLk9SxXaxlzJiavvMRc8BYutNMEnrxpGXi1kwZKFpTszQKhj2qpbLQ5WwoAop87bdFzvUKES0g888cXVMlmiRbWehvDrgFTO337j4tmAvNkEe4bOX7G8ygO6TqpAkDTD_FVIta4gzf9N_4XiOsQ-YEKuaZtLs8TLMqWw2CF9zQJ2s4hRGLQ7bK3Q9QwqgFwOMUi_VPgDX5yHwtPEb65RocZemxKBEe5VcltU_B7jC0Zix0bJbO5mdupSU3hzea9_oQSiWnA",
    "refresh_token": "sKqBzFkLXy0YgMjipjnwdetF7U9dx-kh-fjEaeUDM4JuEg7UI7_tSyDsftsbVxyGlD_nlQwX49nsipigBMXgOESkTkOvvsIFuC6kguuE_dDS0Pys0NrILNw_oQYGE7ZT",
    "scope": "read openid profile",
    "id_token": "eyJraWQiOiI3MGQzZjhhMS04ZDZlLTQyYzItODJjZS02NTY5MWE4OGY3ZTUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJzaHViaGFtIiwiYXVkIjoibXktY2xpZW50IiwiYXpwIjoibXktY2xpZW50IiwiYXV0aF90aW1lIjoxNzE4MDM1ODI5LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjkwMDAiLCJleHAiOjE3MTgwMzgxNTMsImlhdCI6MTcxODAzNjM1MywianRpIjoiODA0ZDIwNTAtODU5My00MzMzLTg2MGItNjE2MWE0OTQwMjA1Iiwic2lkIjoicUdLcV9jQVFUdE9ScmZ6bDFJWlFKcWNobGRkYUlyQ0F4SmZncV9JS3IyZyJ9.c4-zr7_7WErlmWpHt0tUvAaBNdM92xDEDRPqw00nl8BRSbYxQTYkddaS4rM7qUWQX-c8E3Ow6B0wjjKLxeEYbOEM3tM3Oq88u0RcdBYsKfJRvrCJT1zGtEwNvb5Qw5WcUHTXM7Jbo5Kl_X-s4Mz3SRlBeJKaTOjwgQ94sywyfYQwcMdEU4of-RR8ySXat3XRhtVDPRDCuWZnsBYBng4ApreHXoq7rplqxFj7BTZALyamYaezskKSwwBnB6B2cTkY_4cH-nHTHv8eX-F6q1nspSxfJ2k2ggKgibZReSU5HM1Mfxt_m2-51EO9gh9IhwWeseIH7_TMkxJRQscrUdJCWg",
    "token_type": "Bearer",
    "expires_in": 299
  }

### 5. JSON Web Key Set Endpoint
- **Endpoint:** `/oauth2/jwks`
- **Method:** GET
- **Description:** JSON Web Key Set (JWKS) endpoint providing public keys for validating JWTs issued by the Authorization Server.
- **Request:**
  ```bash
  curl --location 'http://<auth-server-url>/oauth2/jwks'

- **Response:**
  ```json
  {
    "keys": [
        {
            "kty": "RSA",
            "e": "AQAB",
            "kid": "70d3f8a1-8d6e-42c2-82ce-65691a87tye5",
            "n": "taG7j67SLjgCbTK7YQfZlGNHxPjuK5Sz6oGFdazJpsMG-Tiz6YICaLY5x5mVqDWTBPKS87NCrv2RMvhY-RzrXakd-Qe8V6ZNGdKJEat2noXvSICkNBvLyonwR5tf6lI8ALjIar1ZBgm_CHjx5J5WUAlgETa1weayN8sFpTUz-kMlawsvQYqlXiOWKgi2I9RSxYT_KjOTO4hleOryvxG3054QrsjeeowefQtEKz1XWqKk6o6JXWIUDlN7FQ5M8AvIjmyrucXZWxb_L9_c8SrM7EYyitP25poYL5c-9P2ACnulWxxkhltXFalLszU6LDY2pNMIRFZWA9wdwAOm_JmQ"
        }
    ]
  }

### 6. Token Introspection Endpoint
- **Endpoint:** `/oauth2/introspect`
- **Method:** POST
- **Description:** Token introspection endpoint for checking the validity and details of an OAuth2 token.
- **Request:**
  ```bash
  curl --location 'http://<auth-server-url>/oauth2/introspect' \
  --header 'Authorization: Basic <client-creds-in-base64>' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data-urlencode 'token=<access-token-to-introspect>'

- **Response:**
  ```json
  {
    "active": true,
    "sub": "my-client",
    "aud": [
        "my-client"
    ],
    "nbf": 1718123071,
    "scope": "read openid profile",
    "iss": "http://localhost:9000",
    "exp": 1718123371,
    "iat": 1718123071,
    "jti": "f0eedea3-204b-435c-865f-690e14af2517",
    "client_id": "my-client",
    "token_type": "Bearer"
  }

### 7. Token Revocation Endpoint
- **Endpoint:** `/oauth2/revoke`
- **Method:** POST
- **Description:** Token revocation endpoint for revoking an OAuth2 token.
- **Request:**
  ```bash
  curl --location 'http://<auth-server-url>/oauth2/revoke' \
  --header 'Authorization: Basic <client-creds-in-base64>' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --data-urlencode 'token=<refresh-token-to-introspect>' \
  --data-urlencode 'token_type_hint=refresh_token'

- **Response:**
  ```json
  200 OK

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
