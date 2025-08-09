package com.example.user.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Add this annotation
public class KeycloakConfig {

    @Value("${app.keycloak.admin.clientId}")  // Added closing brace
    private String clientId;

    @Value("${app.keycloak.admin.clientSecret}")  // Added closing brace
    private String clientSecret;

    @Value("WiseWander_v1.1")  // Added closing brace
    private String realm;

    @Value("${app.keycloak.serverUrl}")  // Fixed typo (severUrl -> serverUrl)
    private String serverUrl;  // Fixed variable name

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder
                .builder()
                .serverUrl(serverUrl)  // Use corrected variable name
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType("client_credentials")
                .build();
    }
}