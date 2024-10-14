package com.springpj.heroesspellservice.controller;

import com.springpj.heroesspellservice.model.dto.SpellDto;
import com.springpj.heroesspellservice.service.SpellService;
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
@RequestMapping("spell")
public class SpellController {
	
	private static final Logger log = LoggerFactory.getLogger(SpellController.class);
	private final SpellService spellService;

	public SpellController(SpellService spellService) {
		this.spellService = spellService;
	}
	
	@GetMapping("{id}")
	public EntityModel<SpellDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		SpellDto spell = spellService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<SpellDto> spellModel = EntityModel.of(spell);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(spell.getId())).withRel("self");

		spellModel.add(selfLink);
		
		return spellModel;
		
	}
	@PostMapping("add")
	public SpellDto save(@RequestBody SpellDto dto) {
		return spellService.save(dto);
	}
	@PutMapping("{id}/update")
	public SpellDto update(@RequestBody SpellDto dto, @PathVariable Long id) {
		dto.setId(id);
		return spellService.save(dto);
	}

}
