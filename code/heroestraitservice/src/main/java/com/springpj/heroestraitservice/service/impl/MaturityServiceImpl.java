package com.springpj.heroestraitservice.service.impl;

import com.springpj.heroestraitservice.errorhandler.exception.MaturityNotFoundByIdException;
import com.springpj.heroestraitservice.mapper.MaturityMapper;
import com.springpj.heroestraitservice.model.maturity.Maturity;
import com.springpj.heroestraitservice.model.maturity.MaturityDto;
import com.springpj.heroestraitservice.repository.MaturityRepository;
import com.springpj.heroestraitservice.service.MaturityService;
import com.springpj.heroestraitservice.service.MaturityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MaturityServiceImpl implements MaturityService {


	private static final Logger log = LoggerFactory.getLogger(MaturityServiceImpl.class);

	private final MaturityRepository maturityRepository;
	private final MaturityMapper maturityMapper;

	@Autowired
	public MaturityServiceImpl(MaturityRepository maturityRepository, MaturityMapper maturityMapper) {

		this.maturityRepository = maturityRepository;
		this.maturityMapper = maturityMapper;
	}

	@Override
	public MaturityDto save(MaturityDto dto) {

		log.info("Saving maturity with name {} - START", dto.getName());
		Maturity savedMaturity = maturityRepository.save(maturityMapper.toEntity(dto));
		log.info("Saving maturity with name {} - DONE", dto.getName());

		MaturityDto maturityDto = maturityMapper.toDto(savedMaturity);

		return maturityDto;
	}

	@Override
	public MaturityDto findById(Long id) {
		Maturity maturity = maturityRepository.findById(id)
				.orElseThrow(() -> new MaturityNotFoundByIdException(id));
		return maturityMapper.toDto(maturity);
	}


}
