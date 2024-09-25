package com.springpj.heroesauthorizationserver.service.impl;

import com.springpj.heroesauthorizationserver.client.UserClientProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springpj.heroesauthorizationserver.dto.LoginRequestDto;
import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.RoleDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;
import com.springpj.heroesauthorizationserver.mapper.UserMapper;
import com.springpj.heroesauthorizationserver.model.user.AccountStatus;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;
import com.springpj.heroesauthorizationserver.service.AuthenticationService;
import reactor.core.publisher.Mono;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	private final ReactiveAuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	private final UserClientProxy userClient;

	public AuthenticationServiceImpl(ReactiveAuthenticationManager authenticationManager, PasswordEncoder passwordEncoder,
			UserMapper userMapper, UserClientProxy userClient) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.userClient = userClient;
	}

	@Override
	public Mono<UserDto> login(LoginRequestDto loginRequestDto) {
		authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());

		return userClient.findByUsername(loginRequestDto.getUsername());
	}

	@Override
	public Mono<UserPrincipal> getUserPrincipal(String username) {

		Mono<UserDto> user = userClient.findByUsername(username);

		return user.map(UserPrincipal::new);
	}

	@Override
	public Mono<UserDto> register(RegisterRequestDto registerRequestDto) {

		Mono<UserDto> user = prepareUserForRegistration(registerRequestDto);
		return user.map(u -> userClient.save(u).block());
	}

	private Mono<UserDto> prepareUserForRegistration(RegisterRequestDto registerRequestDto) {
		UserDto result = userMapper.toUserDto(registerRequestDto);

		result.setPassword(passwordEncoder.encode(result.getPassword()));
		result.setAccountStatus(AccountStatus.ACTIVE);
		result.setCredentialsExpired(false);

		Mono<RoleDto> role = userClient.findRoleByName(registerRequestDto.getRoleName());

		return role.map(r -> {
			result.setRoleId(r.getId());
			return result;
		});
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
