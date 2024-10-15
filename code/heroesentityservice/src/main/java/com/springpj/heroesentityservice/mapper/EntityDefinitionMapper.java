package com.springpj.heroesentityservice.mapper;
import com.springpj.heroesentityservice.model.entity.EntityDefinition;
import com.springpj.heroesentityservice.model.entity.EntityDefinitionDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EntityDefinitionMapper {
	
	EntityDefinitionDto toDto(EntityDefinition entityDefinition);
	EntityDefinition toEntity(EntityDefinitionDto entityDefinitionDto);

	List<EntityDefinition> toEntityList(List<EntityDefinitionDto> entityDefinitionDtos);
	List<EntityDefinitionDto> toDtoList(List<EntityDefinition> entityDefinitions);

}
