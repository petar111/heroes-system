package com.springpj.heroesentityservice.service;

import com.springpj.heroesentityservice.model.level.LevelDto;

public interface LevelService {
	
	LevelDto save(LevelDto dto);
	LevelDto findById(Long id);


}
