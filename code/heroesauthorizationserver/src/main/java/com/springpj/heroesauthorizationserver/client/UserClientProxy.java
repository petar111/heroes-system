package com.springpj.heroesauthorizationserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springpj.heroesauthorizationserver.configuration.FeignConfiguration;
import com.springpj.heroesauthorizationserver.dto.UserDto;


@FeignClient(name = ClientConstants.HEROES_CONTENT_CREATOR_APP_NAME, 
				contextId = "user-client-proxy", 
				configuration = FeignConfiguration.class)
public interface UserClientProxy{

	@GetMapping("/user/@/{username}")
	UserDto findByUsername(@PathVariable String username);
	
	@PostMapping("/user/save")
	UserDto save(@RequestBody UserDto user);

	@GetMapping("/user/email/{email}")
	UserDto findByEmail(@PathVariable String email);
	
}
