package com.springpj.heroesbattletypeservice.controller;

import com.springpj.heroesbattletypeservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroesbattletypeservice.model.battletype.BattleTypeDto;
import com.springpj.heroesbattletypeservice.service.BattleTypeService;
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
@RequestMapping("battle-type")
public class BattleTypeController {
	
	private static final Logger log = LoggerFactory.getLogger(BattleTypeController.class);
	private final BattleTypeService battleTypeService;

	public BattleTypeController(BattleTypeService battleTypeService) {
		this.battleTypeService = battleTypeService;
	}
	
	@GetMapping("{id}")
	public EntityModel<BattleTypeDto> findById(@PathVariable Long id) {
		log.info("Finding by id:" + id + " - START");
		BattleTypeDto battleType = battleTypeService.findById(id);
		log.info("Finding by id:" + id + " - END");
		
		EntityModel<BattleTypeDto> battleTypeModel = EntityModel.of(battleType);
		
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass())
						.findById(battleType.getId())).withRel("self");

		battleTypeModel.add(selfLink);
		
		return battleTypeModel;
		
	}
	@PostMapping("add")
	public BattleTypeDto save(@RequestBody BattleTypeDto dto) {
		return battleTypeService.save(dto);
	}
	@PutMapping("{id}/update")
	public BattleTypeDto update(@RequestBody BattleTypeDto dto, @PathVariable Long id) {
		dto.setId(id);
		return battleTypeService.save(dto);
	}
	@PostMapping("/capacity/bulkAdd")
	List<BattleCapacityDto> bulkAddCapacities(@RequestBody List<BattleCapacityDto> battleCapacities){
		return battleTypeService.bulkAddCapacities(battleCapacities);
	}

}
