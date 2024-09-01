-- Flyway Init migration V2 - Insert initial data.
-- SQL script generated with DBeaver

INSERT INTO `role`
(id, description, `role`)
VALUES('6bb9687b-72e1-4110-95f6-7820b7e2d2d8', 'Admin Access', 'admin');

INSERT INTO `scope`
(id, description, `scope`)
VALUES('0be0f7cb-d3e8-4ebb-8b86-8800d20b6b80', 'Read/Write Permission for OpenID Profile', 'profile');
INSERT INTO `scope`
(id, description, `scope`)
VALUES('ce42b4c1-954e-41c1-9f59-95c4cb943c07', 'Write Permission for Client Endpoint', 'client.read');
INSERT INTO `scope`
(id, description, `scope`)
VALUES('7019ba52-8d82-4cdf-99c2-e90e7c14568a', 'Write Permission for Client Endpoint', 'client.write');


INSERT INTO `user`
(id, address, city, country, email, is_account_expired, is_account_locked, is_active, is_credentials_expired, mobile, password, username)
VALUES('f0a6ff2f-31fb-4913-b1ec-e7e980c9a874', NULL, '', NULL, 'admin@localhost.com', 0, 0, 1, 0, '', '$2a$10$8jFz1sK52CszBb2HWgz3ROPjfZAbjDKeBA.aynWREkU1cS.5P0acm', 'admin');


INSERT INTO user_role
(user_id, role_id)
VALUES('f0a6ff2f-31fb-4913-b1ec-e7e980c9a874', '6bb9687b-72e1-4110-95f6-7820b7e2d2d8');

INSERT INTO client
(id, authorization_grant_types, client_authentication_methods, client_id, client_id_issued_at, client_name, client_secret, client_secret_expires_at, client_settings, post_logout_redirect_uris, redirect_uris, scopes, token_settings)
VALUES('dccd5b970e054ce6b9ce0dbba38254d4', 'client_credentials,authorization_code', 'client_secret_basic', 'dccd5b970e054ce6b9ce0dbba38254d4', '2024-09-01 15:50:45.759', 'registerar-client', '$2a$10$SFuQmBC9NWmpdkh0rYR5LOiMKOp7j5zgjGPA91sQNl38wiFfCOyEq', NULL, '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '', 'https://localhost:9000/login,https://oauth.pstmn.io/v1/callback', 'profile,client.read,client.write', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}');