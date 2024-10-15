package com.springpj.heroesentityservice.service.impl;

import com.springpj.heroesentityservice.errorhandler.exception.LevelNotFoundByIdException;
import com.springpj.heroesentityservice.mapper.LevelMapper;
import com.springpj.heroesentityservice.model.level.LevelDto;
import com.springpj.heroesentityservice.repository.LevelRepository;
import com.springpj.heroesentityservice.service.LevelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LevelServiceImpl implements LevelService {


	private static final Logger log = LoggerFactory.getLogger(LevelServiceImpl.class);

	private final LevelRepository levelRepository;
	private final LevelMapper levelMapper;

	@Autowired
	public LevelServiceImpl(LevelRepository levelRepository, LevelMapper levelMapper) {
		this.levelRepository = levelRepository;
		this.levelMapper = levelMapper;
	}


	@Override
	public LevelDto save(LevelDto dto) {
		return levelMapper.toDto(levelRepository.save(levelMapper.toEntity(dto)));
	}

	@Override
	public LevelDto findById(Long id) {
		return levelMapper.toDto(levelRepository.findById(id).orElseThrow(() -> new LevelNotFoundByIdException(id)));
	}
}
