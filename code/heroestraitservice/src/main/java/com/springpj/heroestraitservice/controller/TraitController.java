package com.springpj.heroestraitservice.controller;

import com.springpj.heroestraitservice.model.dto.TraitDto;
import com.springpj.heroestraitservice.service.TraitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.List;

@RestController
@RequestMapping("trait")
public class TraitController {
	
	private static final Logger log = LoggerFactory.getLogger(TraitController.class);
	private final TraitService traitService;

	public TraitController(TraitService traitService) {
		this.traitService = traitService;
	}
	
	@GetMapping("{id}")
	public EntityModel<TraitDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		TraitDto trait = traitService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<TraitDto> traitModel = EntityModel.of(trait);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(trait.getId())).withRel("self");

		traitModel.add(selfLink);
		
		return traitModel;
		
	}
	@PostMapping("add")
	public TraitDto save(@RequestBody TraitDto dto) {
		return traitService.save(dto);
	}
	@PutMapping("{id}/update")
	public TraitDto update(@RequestBody TraitDto dto, @PathVariable Long id) {
		dto.setId(id);
		return traitService.save(dto);
	}

}
