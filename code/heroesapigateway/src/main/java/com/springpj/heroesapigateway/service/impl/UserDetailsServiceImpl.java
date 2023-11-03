package com.springpj.heroesapigateway.service.impl;

import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springpj.heroesapigateway.client.UserClientProxy;
import com.springpj.heroesapigateway.model.user.UserPrincipal;

import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserClientProxy userClient;

    public UserDetailsServiceImpl(UserClientProxy userClient) {
        this.userClient = userClient;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
    	
    	UserDetails result = new UserPrincipal(userClient.findByUsername(username));
        
        return Mono.just(result);
    }


}
