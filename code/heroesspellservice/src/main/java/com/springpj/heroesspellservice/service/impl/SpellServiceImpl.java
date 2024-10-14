package com.springpj.heroesspellservice.service.impl;

import com.springpj.heroesspellservice.errorhandler.exception.SpellNotFoundByIdException;
import com.springpj.heroesspellservice.mapper.SpellMapper;
import com.springpj.heroesspellservice.model.spell.Spell;
import com.springpj.heroesspellservice.model.dto.SpellDto;
import com.springpj.heroesspellservice.repository.SpellRepository;
import com.springpj.heroesspellservice.service.SpellService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SpellServiceImpl implements SpellService {


	private static final Logger log = LoggerFactory.getLogger(SpellServiceImpl.class);

	private final SpellRepository spellRepository;
	private final SpellMapper spellMapper;

	@Autowired
	public SpellServiceImpl(SpellRepository spellRepository, SpellMapper spellMapper) {

		this.spellRepository = spellRepository;
		this.spellMapper = spellMapper;
	}

	@Override
	public SpellDto save(SpellDto dto) {

		log.info("Saving spell with name {} - START", dto.getName());
		Spell savedSpell = spellRepository.save(spellMapper.toEntity(dto));
		log.info("Saving spell with name {} - DONE", dto.getName());

		SpellDto spellDto = spellMapper.toDto(savedSpell);

		return spellDto;
	}

	@Override
	public SpellDto findById(Long id) {
		Spell spell = spellRepository.findById(id)
				.orElseThrow(() -> new SpellNotFoundByIdException(id));
		return spellMapper.toDto(spell);
	}


}
