package com.springpj.heroescontentcreator.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.springpj.heroescontentcreator.model.dto.RegisterRequestDto;
import com.springpj.heroescontentcreator.model.dto.UserDto;
import com.springpj.heroescontentcreator.model.user.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    @Mapping(source = "roleId", target = "role.id")
    User toEntity(UserDto userDto);

    User toEntity(RegisterRequestDto registerRequestDto);

}
