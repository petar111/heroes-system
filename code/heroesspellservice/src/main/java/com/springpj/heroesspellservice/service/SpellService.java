package com.springpj.heroesspellservice.service;

import com.springpj.heroesspellservice.model.dto.SpellDto;

import java.util.List;

public interface SpellService {
	
	SpellDto save(SpellDto dto);

	SpellDto findById(Long id);
}
