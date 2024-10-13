package com.springpj.heroesbattletypeservice.service;

import com.springpj.heroesbattletypeservice.model.dto.BattleCapacityDto;
import com.springpj.heroesbattletypeservice.model.dto.BattleTypeDto;

import java.util.List;

public interface BattleTypeService {
	
	BattleTypeDto save(BattleTypeDto dto);

	BattleTypeDto findById(Long id);

    List<BattleCapacityDto> bulkAddCapacities(List<BattleCapacityDto> battleCapacities);
}
