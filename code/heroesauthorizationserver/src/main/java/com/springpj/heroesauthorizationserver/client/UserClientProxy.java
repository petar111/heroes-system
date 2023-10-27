package com.springpj.heroesauthorizationserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springpj.heroesauthorizationserver.dto.UserDto;

@FeignClient(name = ClientConstants.HEROES_CONTENT_CREATOR_APP_NAME, contextId = "user-client-proxy")
public interface UserClientProxy{

	@GetMapping("/user/{username}")
	UserDto findByUsername(@PathVariable("username") String username);
	
	@PostMapping("/user/save")
	UserDto save(@RequestBody UserDto user);
	
}
