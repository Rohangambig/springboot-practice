package com.entire.demo.config;

import com.entire.demo.lib.JwtImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(customize ->
                customize.requestMatchers("/login","/register").permitAll()
                        .anyRequest().authenticated()
        );

        http.httpBasic(Customizer.withDefaults());
        http.sessionManagement(customize -> customize.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(JwtImpl);
        return http.build();
    }

}
