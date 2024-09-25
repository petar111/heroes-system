package com.springpj.heroesauthorizationserver.service;

import com.springpj.heroesauthorizationserver.dto.LoginRequestDto;
import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
	
	Mono<UserDto> login(LoginRequestDto loginRequestDto);

    Mono<UserPrincipal> getUserPrincipal(String username);

    Mono<UserDto> register(RegisterRequestDto registerRequestDto);

}
