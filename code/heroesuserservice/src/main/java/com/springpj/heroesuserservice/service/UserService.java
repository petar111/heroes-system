package com.springpj.heroesuserservice.service;


import com.springpj.heroesuserservice.model.dto.UserDto;
import com.springpj.heroesuserservice.model.dto.paging.PageRequestDto;
import org.springframework.data.domain.Page;

public interface UserService {
	
	UserDto findById(Long id);
	
    UserDto findByUsername(String username);

    UserDto save(UserDto userDto);

	Page<UserDto> findAllPage(PageRequestDto pageRequest);

	UserDto findByEmail(String email);

}
