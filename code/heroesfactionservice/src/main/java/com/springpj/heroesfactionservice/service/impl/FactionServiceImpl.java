package com.springpj.heroesfactionservice.service.impl;

import java.util.List;

import com.springpj.heroesfactionservice.repository.FactionRepository;
import com.springpj.heroesfactionservice.repository.FactionVersionRepository;
import com.springpj.heroesfactionservice.service.FactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroesfactionservice.errorhandler.exception.FactionNotFoundByIdException;
import com.springpj.heroesfactionservice.mapper.FactionMapper;
import com.springpj.heroesfactionservice.mapper.FactionVersionMapper;
import com.springpj.heroesfactionservice.model.dto.FactionDto;
import com.springpj.heroesfactionservice.model.dto.FactionVersionDto;
import com.springpj.heroesfactionservice.model.faction.Faction;

@Service
public class FactionServiceImpl implements FactionService {
	
	private final FactionRepository factionRepository;
	private final FactionVersionRepository factionVersionRepository;
	private final FactionMapper factionMapper;
	private final FactionVersionMapper factionVersionMapper;
	
	@Autowired
	public FactionServiceImpl(FactionRepository factionRepository,
							  FactionVersionRepository factionVersionRepository,
							  FactionMapper factionMapper,
							  FactionVersionMapper factionVersionMapper) {
		
		this.factionRepository = factionRepository;
		this.factionMapper = factionMapper;
		this.factionVersionRepository = factionVersionRepository;
		this.factionVersionMapper = factionVersionMapper;
	}

	@Override
	public FactionDto save(FactionDto dto) {
		Faction savedFaction = factionRepository.save(factionMapper.toEntity(dto));
//		factionVersionRepository.save(factionMapper.toVersionEntity(savedFaction));
		return factionMapper.toDto(savedFaction);
	}

	@Override
	public FactionDto findById(Long id) {
		Faction faction = factionRepository.findById(id)
				.orElseThrow(() -> new FactionNotFoundByIdException(id));
		return factionMapper.toDto(faction);
	}

	@Override
	public List<FactionVersionDto> findAllVersionsById(Long id) {
		if(!factionRepository.existsById(id)) {
			throw new FactionNotFoundByIdException(id);
		}
		Faction faction = new Faction();
		faction.setId(id);
		return factionVersionMapper.toDtoList(factionVersionRepository.findAllByFaction(faction));
	}

	@Override
	public FactionDto deleteById(Long id) {
		FactionDto deletedFaction = factionMapper.toDto(factionRepository.findById(id).orElseThrow(() -> new FactionNotFoundByIdException(id)));
		factionRepository.deleteById(id);
		return deletedFaction;
	}


}
