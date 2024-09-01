package com.userservice.authorization.configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.*;
import java.util.stream.Collectors;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import com.userservice.authorization.configuration.properties.RsaKeyProperties;
import com.userservice.authorization.exception.handler.AccessDeniedHandlerImpl;
import com.userservice.authorization.exception.handler.AuthenticationEntryPointImpl;
import com.userservice.authorization.exception.handler.BearerTokenAuthenticationEntryPointImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {
    private final AccessDeniedHandlerImpl accessDeniedHandlerImpl;
    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;
    private final BearerTokenAuthenticationEntryPointImpl myBearerTokenAuthenticationEntryPoint;
    private final RsaKeyProperties rsaKeys;

    SecurityConfiguration(AccessDeniedHandlerImpl accessDeniedHandlerImpl,
                          AuthenticationEntryPointImpl authenticationEntryPointImpl,
                          BearerTokenAuthenticationEntryPointImpl myBearerTokenAuthenticationEntryPoint,
                          RsaKeyProperties                      rsaKeys) {
        this.accessDeniedHandlerImpl = accessDeniedHandlerImpl;
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
        this.myBearerTokenAuthenticationEntryPoint = myBearerTokenAuthenticationEntryPoint;
        this.rsaKeys = rsaKeys;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
            throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http
                .getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());	// Enable OpenID Connect 1.0
        http
                // Redirect to the login page when not authenticated from the
                // authorization endpoint
                .exceptionHandling((exceptions) -> exceptions
                        .defaultAuthenticationEntryPointFor(
                                new LoginUrlAuthenticationEntryPoint("/login"),
                                new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                        )
                );

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/V1/user/signup").permitAll()
                        .requestMatchers("/api/V1/user/**").hasAnyAuthority("SCOPE_profile", "ROLE_admin")
                        .requestMatchers("/api/V1/client/**").hasAnyAuthority("SCOPE_client.read", "ROLE_admin")
                        .requestMatchers("/api/V1/client/register").hasAuthority("SCOPE_client.write")
                        .anyRequest().authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                // If I add 'oauth2ResourceServer' in SecurityFilterChain order(1) spring gives
                // access denied for every request.
                .oauth2ResourceServer((resourceServer) -> resourceServer    // Accept access tokens for User Info and/or Client Registration
                        .jwt(Customizer.withDefaults())
                        // This code is added to handle entry point exceptions by ControllerAdvice,
                        // else these exceptions didn't reach controllers.
                        .authenticationEntryPoint(this.myBearerTokenAuthenticationEntryPoint)
                        .accessDeniedHandler(this.accessDeniedHandlerImpl))
                .httpBasic(httpBasic -> httpBasic.authenticationEntryPoint(this.authenticationEntryPointImpl))
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
//                .formLogin(Customizer.withDefaults());
//                .headers(header->{header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);})
                .formLogin(form -> form.loginPage("/login").permitAll());
        return http.build();
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAPublicKey publicKey = rsaKeys.getPublicKey();
        RSAPrivateKey privateKey = rsaKeys.getPrivateKey();
        String keyID = rsaKeys.getKeyID();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(keyID)
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer() {
        return (context) -> {
            if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType())) {
                context.getClaims().claims((claims) -> {
                    try {
                        // Get the User Details Object
                        UserDetails userDetails = getMyUserDetailsFromAuthorizations(
                                    Objects.requireNonNull(context.getAuthorization()));

                        // In case other than Authorization Code User details can be null.
                        if (userDetails != null) {
                            Set<String> roles = AuthorityUtils.authorityListToSet(userDetails.getAuthorities())
                                    .stream()
                                    .map(c -> c.replaceFirst("^ROLE_", ""))
                                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
                            claims.put("roles", roles);
                        }
                    }catch (Exception e) {
                        log.warn("Error Reading UserData : " + e);
                    }
                });
            }
        };
    }

    private UserDetails getMyUserDetailsFromAuthorizations(OAuth2Authorization authorization) {
        Map<String, Object> authorizations = authorization.getAttributes();
        UsernamePasswordAuthenticationToken usernamePasswdAuthToken =
                (UsernamePasswordAuthenticationToken) authorizations.get("java.security.Principal");
        return (UserDetails) usernamePasswdAuthToken.getPrincipal();
    }
}