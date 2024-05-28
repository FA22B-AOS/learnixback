package de.szut.learnixback.securityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(2)
public class SecurityConfiguration {

    @Bean
    @Order(1)
    public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/**").permitAll());
                        .anyRequest().permitAll());

        return http.build();

    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainAuthorization(HttpSecurity http) throws Exception {
         http
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().authenticated()
                )
                .addFilterAfter(new CustomSecurityFilter(), BasicAuthenticationFilter.class);

         return http.build();

    }
}
