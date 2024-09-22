package com.springpj.heroesoriginservice.service;

import org.springframework.data.domain.Page;

import com.springpj.heroesoriginservice.model.dto.UserDto;
import com.springpj.heroesoriginservice.model.dto.paging.PageRequestDto;

public interface UserService {
	
	UserDto findById(Long id);
	
    UserDto findByUsername(String username);

    UserDto save(UserDto userDto);

	Page<UserDto> findAllPage(PageRequestDto pageRequest);

	UserDto findByEmail(String email);

}
