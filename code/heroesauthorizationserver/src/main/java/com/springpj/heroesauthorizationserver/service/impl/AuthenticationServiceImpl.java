package com.springpj.heroesauthorizationserver.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springpj.heroesauthorizationserver.client.RoleClientProxy;
import com.springpj.heroesauthorizationserver.client.UserClientProxy;
import com.springpj.heroesauthorizationserver.dto.LoginRequestDto;
import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.RoleDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;
import com.springpj.heroesauthorizationserver.mapper.UserMapper;
import com.springpj.heroesauthorizationserver.model.user.AccountStatus;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;
import com.springpj.heroesauthorizationserver.service.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

	private final AuthenticationManager authenticationManager;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	private final UserClientProxy userClient;
	private final RoleClientProxy roleClient;

	public AuthenticationServiceImpl(AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder, UserMapper userMapper, UserClientProxy userClient, RoleClientProxy roleClient) {
		this.authenticationManager = authenticationManager;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
		this.userClient = userClient;
		this.roleClient = roleClient;
	}

	@Override
	public UserDto login(LoginRequestDto loginRequestDto) {
		authenticate(loginRequestDto.getUsername(), loginRequestDto.getPassword());
		
		return null;
	}

	@Override
	public UserPrincipal getUserPrincipal(String username) {
		return new UserPrincipal(
				userClient.findByUsername(username));
	}

	@Override
	public UserDto register(RegisterRequestDto registerRequestDto) {
		
		UserDto user = prepareUserForRegistration(registerRequestDto);

		log.info("User dto: " + user);
		return userClient.save(user);
	}

	private UserDto prepareUserForRegistration(RegisterRequestDto registerRequestDto) {
		UserDto result = userMapper.toUserDto(registerRequestDto);

		result.setPassword(passwordEncoder.encode(result.getPassword()));
		result.setAccountStatus(AccountStatus.ACTIVE);
		result.setCredentialsExpired(false);
		
		RoleDto role = roleClient.findByName(registerRequestDto.getRoleName());
		
		result.setRoleId(role.getId());;

		return result;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
