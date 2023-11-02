package com.springpj.heroesapigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.springpj.heroesapigateway.dto.UserDto;
import com.springpj.heroesapigateway.security.constants.ClientConstants;


@FeignClient(name = ClientConstants.HEROES_CONTENT_CREATOR_APP_NAME, 
				contextId = "user-client-proxy")
public interface UserClientProxy{

	@GetMapping("/user/@/{username}")
	UserDto findByUsername(@PathVariable String username);
	
	@PostMapping("/user/save")
	UserDto save(@RequestBody UserDto user);

	@GetMapping("/user/email/{email}")
	UserDto findByEmail(@PathVariable String email);
	
}
