package com.springpj.heroesauthorizationserver.service.impl;

import com.springpj.heroesauthorizationserver.dto.UserDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final WebClient userClient;

    public UserDetailsServiceImpl(@Qualifier("userServiceClient") WebClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return  userClient.get().uri("/user/@/{username}", username)
                .retrieve().bodyToMono(UserDto.class).map(UserPrincipal::new);
    }
}
