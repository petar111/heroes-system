package com.springpj.heroesoriginservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.springpj.heroesoriginservice.model.dto.UserDto;
import com.springpj.heroesoriginservice.model.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    @Mapping(source = "roleId", target = "role.id")
    User toEntity(UserDto userDto);

}
