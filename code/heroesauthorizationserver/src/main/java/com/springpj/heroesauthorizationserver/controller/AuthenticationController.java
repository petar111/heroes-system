package com.springpj.heroesauthorizationserver.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroesauthorizationserver.dto.LoginRequestDto;
import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;
import com.springpj.heroesauthorizationserver.errorhandler.exception.AuthenticationFailedException;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;
import com.springpj.heroesauthorizationserver.service.AuthenticationService;
import com.springpj.heroesauthorizationserver.token.jwt.JWTTokenProvider;

import jakarta.validation.Valid;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;
    private final JWTTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationService authenticationService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationService = authenticationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public Mono<ResponseEntity<UserDto>> login(@RequestBody @Valid LoginRequestDto loginRequestDto){

        return authenticationService.login(loginRequestDto)
                .map(UserPrincipal::new)
                .map(r -> ResponseEntity.ok().headers(getJwtHeader(r)).body(r.getUser()));
    }
    
    @PostMapping("authenticate")
    public ResponseEntity<UserDto> authenticate(){
    	throw new UnsupportedOperationException();
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> register(@RequestBody @Valid RegisterRequestDto registerRequestDto){

            return authenticationService.register(registerRequestDto).map(UserPrincipal::new)
                    .map(r -> ResponseEntity.ok().headers(getJwtHeader(r)).body(r.getUser()));

    }


    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Jwt-token", jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

}
