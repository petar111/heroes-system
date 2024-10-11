package com.springpj.heroescompanyservice.service.impl;

import java.util.List;

import com.springpj.heroescompanyservice.repository.CompanyRepository;
import com.springpj.heroescompanyservice.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroescompanyservice.errorhandler.exception.CompanyNotFoundByIdException;
import com.springpj.heroescompanyservice.mapper.CompanyMapper;
import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.company.Company;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	
	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository,
							  CompanyMapper companyMapper) {
		
		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
	}

	@Override
	public CompanyDto save(CompanyDto dto) {
		Company savedCompany = companyRepository.save(companyMapper.toEntity(dto));
		return companyMapper.toDto(savedCompany);
	}

	@Override
	public CompanyDto findById(Long id) {
		Company company = companyRepository.findById(id)
				.orElseThrow(() -> new CompanyNotFoundByIdException(id));
		return companyMapper.toDto(company);
	}

	@Override
	public void deleteAllByFactionId(Long id) {
		companyRepository.deleteAllByFactionId(id);
	}


}
