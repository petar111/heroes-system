package com.springpj.heroescontentcreator.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.springpj.heroescontentcreator.model.authorization.Role;
import com.springpj.heroescontentcreator.model.dto.role.RoleDto;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleDto toDto(Role role);

	List<RoleDto> toListDto(List<Role> roles);

	Role toEntity(RoleDto roleDto);

}
