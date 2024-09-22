package com.springpj.heroesuserservice.mapper;


import com.springpj.heroesuserservice.model.authorization.Role;
import com.springpj.heroesuserservice.model.dto.role.RoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleDto toDto(Role role);

	List<RoleDto> toListDto(List<Role> roles);

	Role toEntity(RoleDto roleDto);

}
