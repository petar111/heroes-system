package com.springpj.heroesentityservice.controller;

import com.springpj.heroesentityservice.model.dto.HeroDto;
import com.springpj.heroesentityservice.model.dto.LevelDto;
import com.springpj.heroesentityservice.service.EntityService;
import com.springpj.heroesentityservice.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("level")
public class LevelController {

	private static final Logger log = LoggerFactory.getLogger(LevelController.class);

	private final LevelService levelService;

	@Autowired
	public LevelController(LevelService levelService) {
		this.levelService = levelService;
	}
	
	@GetMapping("{id}")
	public EntityModel<LevelDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		LevelDto level = levelService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<LevelDto> levelModel = EntityModel.of(level);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(level.getId())).withRel("self");

		levelModel.add(selfLink);
		
		return levelModel;
		
	}

	@PostMapping("add")
	public LevelDto save(@RequestBody LevelDto dto) {
		return levelService.save(dto);
	}


}
