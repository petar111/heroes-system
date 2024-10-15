package com.springpj.heroesentityservice.controller;

import com.springpj.heroesentityservice.model.entity.EntityDefinitionDto;
import com.springpj.heroesentityservice.service.EntityService;
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

@RestController
@RequestMapping("entity-definition")
public class EntityController {
	
	private static final Logger log = LoggerFactory.getLogger(EntityController.class);
	
	private final EntityService entityService;
	
	@Autowired
	public EntityController(EntityService entityService) {
		this.entityService = entityService;
	}
	
	@GetMapping("{id}")
	public EntityModel<EntityDefinitionDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		EntityDefinitionDto entityDefinition = entityService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<EntityDefinitionDto> entityDefinitionModel = EntityModel.of(entityDefinition);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(entityDefinition.getId())).withRel("self");

		entityDefinitionModel.add(selfLink);
		
		return entityDefinitionModel;
		
	}

	@PostMapping("add")
	public EntityDefinitionDto save(@RequestBody EntityDefinitionDto dto) {
		return entityService.save(dto);
	}
	
	@PutMapping("{id}/update")
	public EntityDefinitionDto update(@RequestBody EntityDefinitionDto dto, @PathVariable Long id) {
		dto.setId(id);
		return entityService.save(dto);
	}

}
