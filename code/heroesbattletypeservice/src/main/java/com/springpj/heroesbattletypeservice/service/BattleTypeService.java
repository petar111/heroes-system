package com.springpj.heroesbattletypeservice.service;

import com.springpj.heroesbattletypeservice.model.dto.BattleTypeDto;

public interface BattleTypeService {
	
	BattleTypeDto save(BattleTypeDto dto);

	BattleTypeDto findById(Long id);

}
