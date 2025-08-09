//package com.example.user.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig {
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> {
//            web.ignoring().requestMatchers(
//                    HttpMethod.POST,
//                    "/public/**",
//                    "/users"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.GET,
//                    "/public/**",
//                    "/users/{id}"  // Added GET by ID to public endpoints
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.DELETE,
//                    "/public/**",
//                    "/users/{id}"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.PUT,
//                    "/public/**",
//                    "/users/{id}/send-verification-email",
//                    "/users/forgot-password"
//            );
//            web.ignoring().requestMatchers(
//                    HttpMethod.OPTIONS,
//                    "/**"
//            ).requestMatchers(
//                    "/v3/api-docs/**",
//                    "/configuration/**",
//                    "/swagger-ui/**",
//                    "/swagger-resources/**",
//                    "/swagger-ui.html",
//                    "/webjars/**",
//                    "/api-docs/**"
//            );
//        };
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest()
//                        .authenticated()
//                )
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .build();
//    }
//}