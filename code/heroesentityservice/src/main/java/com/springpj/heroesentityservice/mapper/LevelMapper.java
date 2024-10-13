package com.springpj.heroesentityservice.mapper;

import com.springpj.heroesentityservice.model.dto.LevelDto;
import com.springpj.heroesentityservice.model.level.Level;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface LevelMapper {
	
	LevelDto toDto(Level entityDefinition);
	Level toEntity(LevelDto entityDefinitionDto);


}
