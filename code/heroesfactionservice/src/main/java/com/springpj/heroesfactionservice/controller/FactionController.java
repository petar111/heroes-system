package com.springpj.heroesfactionservice.controller;

import java.util.List;

import com.springpj.heroesfactionservice.messaging.kafka.KafkaCompanyServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.springpj.heroesfactionservice.model.dto.FactionDto;
import com.springpj.heroesfactionservice.model.dto.FactionVersionDto;
import com.springpj.heroesfactionservice.service.FactionService;

@RestController
@RequestMapping("faction")
public class FactionController {
	
	private static final Logger log = LoggerFactory.getLogger(FactionController.class);

	private final KafkaCompanyServiceHandler kafkaCompanyServiceHandler;
	
	private final FactionService factionService;
	
	private final MessageSource messageSoruce;
	
	@Autowired
	public FactionController(KafkaCompanyServiceHandler kafkaCompanyServiceHandler, FactionService factionService,
							 MessageSource messageSource) {
		this.kafkaCompanyServiceHandler = kafkaCompanyServiceHandler;
		this.factionService = factionService;
		this.messageSoruce = messageSource;
	}
	
	@GetMapping("{id}")
	public EntityModel<FactionDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		FactionDto faction = factionService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<FactionDto> factionModel = EntityModel.of(faction);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(faction.getId())).withRel("self");

		factionModel.add(selfLink);
		
		return factionModel;
		
	}
	
	@GetMapping("{id}/versions")
	public List<FactionVersionDto> findAllVersionsById(@PathVariable Long id) {
		
		List<FactionVersionDto> versions = factionService.findAllVersionsById(id);
		
		return versions;
		
	}

	@PostMapping("add")
	public FactionDto save(@RequestBody FactionDto dto) {
		return factionService.save(dto);
	}
	
	@PutMapping("{id}/update")
	public FactionDto update(@RequestBody FactionDto dto, @PathVariable Long id) {
		dto.setId(id);
		return factionService.save(dto);
	}

	@DeleteMapping("{id}")
	public FactionDto delete(@PathVariable Long id) {
		FactionDto deletedFaction = factionService.deleteById(id);
		kafkaCompanyServiceHandler.onFactionDeleted(deletedFaction);
		return deletedFaction;
	}

}
