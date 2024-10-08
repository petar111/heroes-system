package com.springpj.heroesoriginservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springpj.heroesoriginservice.errorhandler.exception.OriginNotFoundByIdException;
import com.springpj.heroesoriginservice.mapper.OriginMapper;
import com.springpj.heroesoriginservice.mapper.OriginVersionMapper;
import com.springpj.heroesoriginservice.model.dto.OriginDto;
import com.springpj.heroesoriginservice.model.dto.OriginVersionDto;
import com.springpj.heroesoriginservice.model.origin.Origin;
import com.springpj.heroesoriginservice.repository.OriginRepository;
import com.springpj.heroesoriginservice.repository.OriginVersionRepository;
import com.springpj.heroesoriginservice.service.OriginService;

@Service
public class OriginServiceImpl implements OriginService {
	
	private final OriginRepository originRepository;
	private final OriginVersionRepository originVersionRepository;
	private final OriginMapper originMapper;
	private final OriginVersionMapper originVersionMapper;
	
	@Autowired
	public OriginServiceImpl(OriginRepository originRepository,
			OriginVersionRepository originVersionRepository,
			OriginMapper originMapper,
			OriginVersionMapper originVersionMapper) {
		
		this.originRepository = originRepository;
		this.originMapper = originMapper;
		this.originVersionRepository = originVersionRepository;
		this.originVersionMapper = originVersionMapper;
	}

	@Override
	public OriginDto save(OriginDto dto) {
		Origin savedOrigin = originRepository.save(originMapper.toEntity(dto));
		originVersionRepository.save(originMapper.toVersionEntity(savedOrigin));
		return originMapper.toDto(savedOrigin);
	}

	@Override
	public OriginDto findById(Long id) {
		Origin origin = originRepository.findById(id)
				.orElseThrow(() -> new OriginNotFoundByIdException(id));
		return originMapper.toDto(origin);
	}

	@Override
	public List<OriginVersionDto> findAllVersionsById(Long id) {
		if(!originRepository.existsById(id)) {
			throw new OriginNotFoundByIdException(id);
		}
		Origin origin = new Origin();
		origin.setId(id);
		return originVersionMapper.toDtoList(originVersionRepository.findAllByOrigin(origin));
	}
	
	

}
