package com.springpj.heroesoriginservice.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springpj.heroesoriginservice.errorhandler.exception.UserAlreadyExistsException;
import com.springpj.heroesoriginservice.errorhandler.exception.UserNotFoundByEmailException;
import com.springpj.heroesoriginservice.errorhandler.exception.UserNotFoundByIdException;
import com.springpj.heroesoriginservice.errorhandler.exception.UserNotFoundByUsernameException;
import com.springpj.heroesoriginservice.mapper.UserMapper;
import com.springpj.heroesoriginservice.model.dto.UserDto;
import com.springpj.heroesoriginservice.model.dto.paging.PageRequestDto;
import com.springpj.heroesoriginservice.model.user.User;
import com.springpj.heroesoriginservice.repository.UserRepository;
import com.springpj.heroesoriginservice.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public UserDto findByUsername(String username) {
		return userMapper.toDto(userRepository.findByUsername(username).orElseThrow(
				() -> new UserNotFoundByUsernameException("Usename " + username + " not found- User service.")));
	}

	@Override
	public UserDto save(UserDto userDto) {
		User user = userMapper.toEntity(userDto);

		//TODO: improve validation
		if (user.getId() == null) {
			userRepository.findByUsername(user.getUsername()).ifPresent((u) -> {
				throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
			});
			userRepository.findByEmail(user.getEmail()).ifPresent((u) -> {
				throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
			});
		}

		User savedUser = userRepository.save(user);
		return userMapper.toDto(savedUser);
	}

	@Override
	public UserDto findById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
		return userMapper.toDto(user);
	}

	@Override
	public Page<UserDto> findAllPage(PageRequestDto pageRequestDto) {

		PageRequest pageRequest = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getPageSize(),
				Sort.by(Direction.fromString(pageRequestDto.getSortOrder()), pageRequestDto.getSortBy()));

		Page<User> users = userRepository.findAll(pageRequest);

		return users.map(userMapper::toDto);
	}

	@Override
	public UserDto findByEmail(String email) {
		return userMapper.toDto(userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundByEmailException("Usename " + email + " not found- User service.")));

	}

}
