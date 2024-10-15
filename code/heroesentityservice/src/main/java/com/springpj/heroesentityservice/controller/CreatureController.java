package com.springpj.heroesentityservice.controller;

import com.springpj.heroesentityservice.model.entity.CreatureDto;
import com.springpj.heroesentityservice.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("creature")
public class CreatureController {

	private static final Logger log = LoggerFactory.getLogger(CreatureController.class);

	private final EntityService entityService;

	@Autowired
	public CreatureController(EntityService entityService) {
		this.entityService = entityService;
	}
	
	@GetMapping("{id}")
	public EntityModel<CreatureDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		CreatureDto creature = entityService.findCreatureById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<CreatureDto> creatureModel = EntityModel.of(creature);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(creature.getId())).withRel("self");

		creatureModel.add(selfLink);
		
		return creatureModel;
		
	}

	@PostMapping("add")
	public CreatureDto save(@RequestBody CreatureDto dto) {
		return entityService.saveCreature(dto);
	}
	
	@PutMapping("{id}/update")
	public CreatureDto update(@RequestBody CreatureDto dto, @PathVariable Long id) {
		dto.setId(id);
		return entityService.saveCreature(dto);
	}

}
