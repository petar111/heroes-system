package com.springpj.heroesentityservice.controller;

import com.springpj.heroesentityservice.model.entity.HeroDto;
import com.springpj.heroesentityservice.service.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hero")
public class HeroController {

	private static final Logger log = LoggerFactory.getLogger(HeroController.class);

	private final EntityService entityService;

	@Autowired
	public HeroController(EntityService entityService) {
		this.entityService = entityService;
	}
	
	@GetMapping("{id}")
	public EntityModel<HeroDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		HeroDto hero = entityService.findHeroById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<HeroDto> heroModel = EntityModel.of(hero);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(hero.getId())).withRel("self");

		heroModel.add(selfLink);
		
		return heroModel;
		
	}

	@PostMapping("add")
	public HeroDto save(@RequestBody HeroDto dto) {
		return entityService.saveHero(dto);
	}
	
	@PutMapping("{id}/update")
	public HeroDto update(@RequestBody HeroDto dto, @PathVariable Long id) {
		dto.setId(id);
		return entityService.saveHero(dto);
	}

}
