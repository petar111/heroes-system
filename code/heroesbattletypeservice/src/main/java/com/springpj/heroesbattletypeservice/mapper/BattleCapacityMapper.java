package com.springpj.heroesbattletypeservice.mapper;
import com.springpj.heroesbattletypeservice.model.battlecapacity.BattleCapacity;
import com.springpj.heroesbattletypeservice.model.battlecapacity.BattleCapacityDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface BattleCapacityMapper {
	
	BattleCapacityDto toDto(BattleCapacity battleCapacity);
	BattleCapacity toEntity(BattleCapacityDto battleCapacityDto);

	List<BattleCapacity> toEntityList(List<BattleCapacityDto> battleCapacityDtos);
	List<BattleCapacityDto> toDtoList(List<BattleCapacity> battleCapacitys);

}
