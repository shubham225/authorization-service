-- Flyway Init migration V2 - Create tables Client, Authorization and Authorization_Consent for spring security entities.
-- SQL script generated with DBeaver

CREATE TABLE `client` (
  `id` varchar(255) NOT NULL,
  `authorization_grant_types` varchar(1000) DEFAULT NULL,
  `client_authentication_methods` varchar(1000) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `client_id_issued_at` datetime(6) DEFAULT NULL,
  `client_name` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `client_secret_expires_at` datetime(6) DEFAULT NULL,
  `client_settings` varchar(2000) DEFAULT NULL,
  `post_logout_redirect_uris` varchar(1000) DEFAULT NULL,
  `redirect_uris` varchar(1000) DEFAULT NULL,
  `scopes` varchar(1000) DEFAULT NULL,
  `token_settings` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `authorization` (
  `id` varchar(255) NOT NULL,
  `access_token_expires_at` datetime(6) DEFAULT NULL,
  `access_token_issued_at` datetime(6) DEFAULT NULL,
  `access_token_metadata` text DEFAULT NULL,
  `access_token_scopes` text DEFAULT NULL,
  `access_token_type` varchar(255) DEFAULT NULL,
  `access_token_value` text DEFAULT NULL,
  `attributes` text DEFAULT NULL,
  `authorization_code_expires_at` datetime(6) DEFAULT NULL,
  `authorization_code_issued_at` datetime(6) DEFAULT NULL,
  `authorization_code_metadata` varchar(255) DEFAULT NULL,
  `authorization_code_value` text DEFAULT NULL,
  `authorization_grant_type` varchar(255) DEFAULT NULL,
  `authorized_scopes` text DEFAULT NULL,
  `device_code_expires_at` datetime(6) DEFAULT NULL,
  `device_code_issued_at` datetime(6) DEFAULT NULL,
  `device_code_metadata` text DEFAULT NULL,
  `device_code_value` text DEFAULT NULL,
  `oidc_id_token_claims` text DEFAULT NULL,
  `oidc_id_token_expires_at` datetime(6) DEFAULT NULL,
  `oidc_id_token_issued_at` datetime(6) DEFAULT NULL,
  `oidc_id_token_metadata` text DEFAULT NULL,
  `oidc_id_token_value` text DEFAULT NULL,
  `principal_name` varchar(255) DEFAULT NULL,
  `refresh_token_expires_at` datetime(6) DEFAULT NULL,
  `refresh_token_issued_at` datetime(6) DEFAULT NULL,
  `refresh_token_metadata` text DEFAULT NULL,
  `refresh_token_value` text DEFAULT NULL,
  `registered_client_id` varchar(255) DEFAULT NULL,
  `state` varchar(500) DEFAULT NULL,
  `user_code_expires_at` datetime(6) DEFAULT NULL,
  `user_code_issued_at` datetime(6) DEFAULT NULL,
  `user_code_metadata` text DEFAULT NULL,
  `user_code_value` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `authorization_consent` (
  `principal_name` varchar(255) NOT NULL,
  `registered_client_id` varchar(255) NOT NULL,
  `authorities` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`principal_name`,`registered_client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;