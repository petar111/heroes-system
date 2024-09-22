package com.springpj.heroesuserservice.mapper;


import com.springpj.heroesuserservice.model.dto.UserDto;
import com.springpj.heroesuserservice.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toListDto(List<User> users);

    @Mapping(source = "roleId", target = "role.id")
    User toEntity(UserDto userDto);

}
