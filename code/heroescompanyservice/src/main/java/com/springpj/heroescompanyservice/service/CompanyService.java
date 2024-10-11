package com.springpj.heroescompanyservice.service;

import java.util.List;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;

public interface CompanyService {
	
	CompanyDto save(CompanyDto dto);

	CompanyDto findById(Long id);

    void deleteAllByFactionId(Long id);
}
