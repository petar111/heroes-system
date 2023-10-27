package com.springpj.heroesauthorizationserver.mapper;

import org.mapstruct.Mapper;

import com.springpj.heroesauthorizationserver.dto.RegisterRequestDto;
import com.springpj.heroesauthorizationserver.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(RegisterRequestDto registerRequestDto);

}
