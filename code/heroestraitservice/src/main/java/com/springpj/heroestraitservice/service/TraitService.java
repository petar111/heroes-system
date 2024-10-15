package com.springpj.heroestraitservice.service;

import com.springpj.heroestraitservice.model.trait.TraitDto;

public interface TraitService {
	
	TraitDto save(TraitDto dto);

	TraitDto findById(Long id);
}
