package com.springpj.heroescompanyservice.service.impl;

import com.springpj.heroescompanyservice.client.FactionClientProxy;
import com.springpj.heroescompanyservice.messaging.kafka.KafkaFactionServiceHandler;
import com.springpj.heroescompanyservice.model.dto.FactionDto;
import com.springpj.heroescompanyservice.repository.CompanyRepository;
import com.springpj.heroescompanyservice.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroescompanyservice.errorhandler.exception.CompanyNotFoundByIdException;
import com.springpj.heroescompanyservice.mapper.CompanyMapper;
import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.company.Company;

import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {


	private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

	private final CompanyRepository companyRepository;
	private final CompanyMapper companyMapper;
	private final FactionClientProxy factionClientProxy;
	private final KafkaFactionServiceHandler kafkaFactionServiceHandler;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository,
							  CompanyMapper companyMapper, FactionClientProxy factionClientProxy, KafkaFactionServiceHandler kafkaFactionServiceHandler) {

		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
		this.factionClientProxy = factionClientProxy;
		this.kafkaFactionServiceHandler = kafkaFactionServiceHandler;
	}

	@Override
	public CompanyDto save(CompanyDto dto) {

		log.info("Finding faction by id {} - START", dto.getFactionId());
		FactionDto factionDto =Optional.of(factionClientProxy.findById(dto.getId()))
				.orElseThrow(() -> new RuntimeException("Faction not found by id: " + dto.getFactionId()));
		log.info("Found faction by id {}, name {}", factionDto.getId(), factionDto.getName());
		log.info("Finding faction by id {} - DONE", dto.getFactionId());

		log.info("Saving company with name {} - START", dto.getName());
		Company savedCompany = companyRepository.save(companyMapper.toEntity(dto));
		log.info("Saving company with name {} - DONE", dto.getName());

		CompanyDto companyDto = companyMapper.toDto(savedCompany);

		log.info("Sending to kafka - START");
		kafkaFactionServiceHandler.onCompanyCreated(companyDto);
		log.info("Sending to kafka - DONE");

		return companyDto;
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
