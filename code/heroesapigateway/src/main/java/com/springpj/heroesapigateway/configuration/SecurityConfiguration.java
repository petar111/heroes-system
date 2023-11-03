package com.springpj.heroesapigateway.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import com.springpj.heroesapigateway.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    private final ReactiveUserDetailsService userDetailsService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    
    public SecurityConfiguration(
            @Qualifier("userDetailsServiceImpl") ReactiveUserDetailsService userDetailsService,
            JwtAuthorizationFilter jwtAuthorizationFilter
            ) {
    	
        this.userDetailsService = userDetailsService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }
    
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
    	log.debug(SecurityWebFilterChain.class + " entry point.");
        http.csrf(csrf -> csrf.disable())
        		.authorizeExchange(e -> e
        				.pathMatchers("/heroes-authorization-server/auth/**").permitAll()
        				.anyExchange().authenticated())
                .authenticationManager(authenticationManager())
                .addFilterBefore(
                		jwtAuthorizationFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }

    
    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
    	UserDetailsRepositoryReactiveAuthenticationManager authManager =
    			new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
    	authManager.setPasswordEncoder(passwordEncoder());
        return authManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    
}
