package com.springpj.heroesbattletypeservice.service;

import com.springpj.heroesbattletypeservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroesbattletypeservice.model.battletype.BattleTypeDto;

import java.util.List;

public interface BattleTypeService {
	
	BattleTypeDto save(BattleTypeDto dto);

	BattleTypeDto findById(Long id);

    List<BattleCapacityDto> bulkAddCapacities(List<BattleCapacityDto> battleCapacities);

	List<BattleCapacityDto> findAllCapacitiesByEntityId(Long id);
}
