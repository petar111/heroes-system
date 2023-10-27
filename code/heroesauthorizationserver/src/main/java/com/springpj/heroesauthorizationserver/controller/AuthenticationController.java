package com.springpj.heroesauthorizationserver.controller;


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


@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
    private final AuthenticationService authenticationService;
    private final JWTTokenProvider jwtTokenProvider;

    public AuthenticationController(AuthenticationService authenticationService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationService = authenticationService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto){

        try {
            UserDto result = authenticationService.login(loginRequestDto);
            HttpHeaders headers = getJwtHeader(authenticationService.getUserPrincipal(loginRequestDto.getUsername()));
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        }catch (Exception ex){
            throw new AuthenticationFailedException("Login failed. Check your username and/or password.");
        }

    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody RegisterRequestDto registerRequestDto){

            UserDto registeredUser = authenticationService.register(registerRequestDto);
            HttpHeaders headers = getJwtHeader(authenticationService.getUserPrincipal(registeredUser.getUsername()));
            return new ResponseEntity<>(registeredUser, headers, HttpStatus.OK);

    }


    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Jwt-token", jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

}
