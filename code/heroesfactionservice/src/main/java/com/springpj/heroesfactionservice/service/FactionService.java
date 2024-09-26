package com.springpj.heroesfactionservice.service;

import java.util.List;

import com.springpj.heroesfactionservice.model.dto.FactionDto;
import com.springpj.heroesfactionservice.model.dto.FactionVersionDto;

public interface FactionService {
	
	FactionDto save(FactionDto dto);

	FactionDto findById(Long id);

	List<FactionVersionDto> findAllVersionsById(Long id);

}
