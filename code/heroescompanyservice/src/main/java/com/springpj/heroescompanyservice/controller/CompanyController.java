package com.springpj.heroescompanyservice.controller;

import com.springpj.heroescompanyservice.messaging.kafka.KafkaFactionServiceHandler;
import com.springpj.heroescompanyservice.model.dto.HeroDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroescompanyservice.model.dto.CompanyDto;
import com.springpj.heroescompanyservice.service.CompanyService;

@RestController
@RequestMapping("company")
public class CompanyController {
	
	private static final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	private final CompanyService companyService;
	
	@Autowired
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}
	
	@GetMapping("{id}")
	public EntityModel<CompanyDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		CompanyDto company = companyService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<CompanyDto> companyModel = EntityModel.of(company);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(company.getId())).withRel("self");

		companyModel.add(selfLink);
		
		return companyModel;
		
	}

	@GetMapping("{id}/lead-hero")
	public HeroDto findLeadHeroById(@PathVariable Long id) {
		return companyService.findLeadHeroByCompanyId(id);

	}

	@PostMapping("add")
	public CompanyDto save(@RequestBody CompanyDto dto) {
		return companyService.save(dto);
	}
	
	@PutMapping("{id}/update")
	public CompanyDto update(@RequestBody CompanyDto dto, @PathVariable Long id) {
		dto.setId(id);
		return companyService.save(dto);
	}

}
