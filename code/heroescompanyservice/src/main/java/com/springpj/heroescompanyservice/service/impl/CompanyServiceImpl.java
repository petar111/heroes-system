package com.springpj.heroescompanyservice.service.impl;

import com.springpj.heroescompanyservice.client.EntityClientProxy;
import com.springpj.heroescompanyservice.client.FactionClientProxy;
import com.springpj.heroescompanyservice.messaging.kafka.KafkaFactionServiceHandler;
import com.springpj.heroescompanyservice.model.company.CreatureInCompany;
import com.springpj.heroescompanyservice.model.company.HeroInCompany;
import com.springpj.heroescompanyservice.model.dto.FactionDto;
import com.springpj.heroescompanyservice.model.dto.HeroDto;
import com.springpj.heroescompanyservice.repository.CompanyRepository;
import com.springpj.heroescompanyservice.repository.CreatureInCompanyRepository;
import com.springpj.heroescompanyservice.repository.HeroInCompanyRepository;
import com.springpj.heroescompanyservice.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroescompanyservice.errorhandler.exception.CompanyNotFoundByIdException;
import com.springpj.heroescompanyservice.service.impl.mapper.CompanyMapper;
import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.model.company.Company;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {


	private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

	private final CompanyRepository companyRepository;
	private final CreatureInCompanyRepository creatureInCompanyRepository;
	private final HeroInCompanyRepository heroInCompanyRepository;
	private final CompanyMapper companyMapper;
	private final FactionClientProxy factionClientProxy;
	private final EntityClientProxy entityClientProxy;
	private final KafkaFactionServiceHandler kafkaFactionServiceHandler;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository,
							  CreatureInCompanyRepository creatureInCompanyRepository, HeroInCompanyRepository heroInCompanyRepository, CompanyMapper companyMapper, FactionClientProxy factionClientProxy, EntityClientProxy entityClientProxy, KafkaFactionServiceHandler kafkaFactionServiceHandler) {

		this.companyRepository = companyRepository;
		this.creatureInCompanyRepository = creatureInCompanyRepository;
		this.heroInCompanyRepository = heroInCompanyRepository;
		this.companyMapper = companyMapper;
		this.factionClientProxy = factionClientProxy;
		this.entityClientProxy = entityClientProxy;
		this.kafkaFactionServiceHandler = kafkaFactionServiceHandler;
	}

	@Override
	public CompanyDto save(CompanyDto dto) {

		FactionDto factionDto = factionClientProxy.findById(dto.getFactionId());


		Company savedCompany = companyRepository.save(companyMapper.toEntity(dto));

		List<CreatureInCompany> creaturesInCompany = dto.getCreaturesInCompany().stream().map(companyMapper::toCreatureInCompanyEntity)
				.toList();
		creaturesInCompany.forEach(c -> c.setCompanyId(savedCompany.getId()));

		List<HeroInCompany> heroesInCompany = dto.getHeroesInCompany().stream().map(companyMapper::toHeroInCompanyEntity).toList();
		heroesInCompany.forEach(h -> h.setCompanyId(savedCompany.getId()));

		creatureInCompanyRepository.saveAll(creaturesInCompany);
		heroInCompanyRepository.saveAll(heroesInCompany);

		CompanyDto companyDto = companyMapper.toDto(savedCompany);
		kafkaFactionServiceHandler.onCompanyCreated(companyDto);

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

	@Override
	public HeroDto findLeadHeroByCompanyId(Long id) {
		CompanyDto company = findById(id);
		return entityClientProxy.findHeroById(company.getLeadHeroId());
	}


}
