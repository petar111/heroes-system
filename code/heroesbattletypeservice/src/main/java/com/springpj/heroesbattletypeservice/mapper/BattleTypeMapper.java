package com.springpj.heroesbattletypeservice.mapper;
import com.springpj.heroesbattletypeservice.model.battletype.BattleType;
import com.springpj.heroesbattletypeservice.model.dto.BattleTypeDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BattleTypeMapper {
	
	BattleTypeDto toDto(BattleType battleType);
	BattleType toEntity(BattleTypeDto battleTypeDto);

	List<BattleType> toEntityList(List<BattleTypeDto> battleTypeDtos);
	List<BattleTypeDto> toDtoList(List<BattleType> battleTypes);

}
