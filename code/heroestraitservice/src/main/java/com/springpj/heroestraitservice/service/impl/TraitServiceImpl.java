package com.springpj.heroestraitservice.service.impl;

import com.springpj.heroestraitservice.errorhandler.exception.TraitNotFoundByIdException;
import com.springpj.heroestraitservice.mapper.TraitMapper;
import com.springpj.heroestraitservice.model.trait.Trait;
import com.springpj.heroestraitservice.model.trait.TraitDto;
import com.springpj.heroestraitservice.repository.TraitRepository;
import com.springpj.heroestraitservice.service.TraitService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TraitServiceImpl implements TraitService {


	private static final Logger log = LoggerFactory.getLogger(TraitServiceImpl.class);

	private final TraitRepository traitRepository;
	private final TraitMapper traitMapper;

	@Autowired
	public TraitServiceImpl(TraitRepository traitRepository, TraitMapper traitMapper) {

		this.traitRepository = traitRepository;
		this.traitMapper = traitMapper;
	}

	@Override
	public TraitDto save(TraitDto dto) {

		log.info("Saving trait with name {} - START", dto.getName());
		Trait savedTrait = traitRepository.save(traitMapper.toEntity(dto));
		log.info("Saving trait with name {} - DONE", dto.getName());

		TraitDto traitDto = traitMapper.toDto(savedTrait);

		return traitDto;
	}

	@Override
	public TraitDto findById(Long id) {
		Trait trait = traitRepository.findById(id)
				.orElseThrow(() -> new TraitNotFoundByIdException(id));
		return traitMapper.toDto(trait);
	}


}
