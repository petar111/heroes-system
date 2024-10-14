package com.springpj.heroestraitservice.service;

import com.springpj.heroestraitservice.model.dto.TraitDto;

import java.util.List;

public interface TraitService {
	
	TraitDto save(TraitDto dto);

	TraitDto findById(Long id);
}
