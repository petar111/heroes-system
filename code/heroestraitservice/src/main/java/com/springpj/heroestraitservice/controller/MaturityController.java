package com.springpj.heroestraitservice.controller;

import com.springpj.heroestraitservice.model.maturity.MaturityDto;
import com.springpj.heroestraitservice.service.MaturityService;
import com.springpj.heroestraitservice.service.MaturityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("maturity")
public class MaturityController {

	private static final Logger log = LoggerFactory.getLogger(MaturityController.class);
	private final MaturityService maturityService;

	public MaturityController(MaturityService maturityService) {
		this.maturityService = maturityService;
	}
	
	@GetMapping("{id}")
	public EntityModel<MaturityDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		MaturityDto maturity = maturityService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<MaturityDto> maturityModel = EntityModel.of(maturity);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(maturity.getId())).withRel("self");

		maturityModel.add(selfLink);
		
		return maturityModel;
		
	}
	@PostMapping("add")
	public MaturityDto save(@RequestBody MaturityDto dto) {
		return maturityService.save(dto);
	}
	@PutMapping("{id}/update")
	public MaturityDto update(@RequestBody MaturityDto dto, @PathVariable Long id) {
		dto.setId(id);
		return maturityService.save(dto);
	}

}
