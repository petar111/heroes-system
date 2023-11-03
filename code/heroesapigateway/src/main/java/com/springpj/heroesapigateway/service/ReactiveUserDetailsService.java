package com.springpj.heroesapigateway.service;

import org.springframework.security.core.userdetails.UserDetails;

import reactor.core.publisher.Mono;

public interface ReactiveUserDetailsService {
	
	Mono<UserDetails> loadUserByUsername(String username);

}
