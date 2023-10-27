package com.springpj.heroesauthorizationserver.service;

import com.springpj.heroesauthorizationserver.dto.LoginRequestDto;
import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;

public interface AuthenticationService {
	
	UserDto login(LoginRequestDto loginRequestDto);

    UserPrincipal getUserPrincipal(String username);

    UserDto register(RegisterRequestDto registerRequestDto);

}
