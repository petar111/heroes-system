package com.springpj.heroesuserservice.controller;

import com.springpj.heroesuserservice.model.dto.UserDto;
import com.springpj.heroesuserservice.model.dto.paging.PageRequestDto;
import com.springpj.heroesuserservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
	
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	private final UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}



	@GetMapping("{id}")
	public UserDto findById(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping("/@/{username}")
	public UserDto findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@GetMapping("/email/{email}")
	public UserDto findByEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
	
	@PostMapping("save")
	public UserDto save(@RequestBody UserDto user) {
		
		return userService.save(user);
	}
	
	@GetMapping("all")
	public Page<UserDto> findAllPage(
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "ASC") String sortOrder,
			@RequestParam(required = false, defaultValue = "username") String sortBy
			){
		
		PageRequestDto pageRequest = PageRequestDto.builder()
				.withPage(page)
				.withPageSize(pageSize)
				.withSortOrder(sortOrder)
				.withSortBy(sortBy)
				.build();
		
		return userService.findAllPage(pageRequest);
	}

}
