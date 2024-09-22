package com.springpj.heroesoriginservice.service;

import java.util.List;

import com.springpj.heroesoriginservice.model.dto.OriginDto;
import com.springpj.heroesoriginservice.model.dto.OriginVersionDto;

public interface OriginService {
	
	OriginDto save(OriginDto dto);

	OriginDto findById(Long id);

	List<OriginVersionDto> findAllVersionsById(Long id);

}
