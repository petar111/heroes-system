package com.springpj.heroesauthorizationserver.client;

import com.springpj.heroesauthorizationserver.dto.RoleDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springpj.heroesauthorizationserver.dto.UserDto;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Component
@ReactiveFeignClient(name = "${SERVICES_HEROESUSERSERVICE_NAME}",
		url = "${SERVICES_HEROESUSERSERVICE_NAME}:${SERVICES_HEROESUSERSERVICE_PORT}")
public interface UserClientProxy{

	@GetMapping("/user/@/{username}")
	Mono<UserDto> findByUsername(@PathVariable String username);
	
	@PostMapping("/user/save")
	Mono<UserDto> save(@RequestBody UserDto user);

	@GetMapping("/user/email/{email}")
	Mono<UserDto> findByEmail(@PathVariable String email);

	@GetMapping("/role/name/{name}")
	Mono<RoleDto> findRoleByName(@PathVariable String name);
	
}
