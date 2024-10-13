package com.springpj.heroescompanyservice.service;

import java.util.List;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.dto.HeroDto;

public interface CompanyService {
	
	CompanyDto save(CompanyDto dto);

	CompanyDto findById(Long id);

    void deleteAllByFactionId(Long id);

    HeroDto findLeadHeroByCompanyId(Long id);
}
