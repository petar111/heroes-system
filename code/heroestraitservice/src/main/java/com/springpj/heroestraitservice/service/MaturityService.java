package com.springpj.heroestraitservice.service;

import com.springpj.heroestraitservice.model.maturity.MaturityDto;

public interface MaturityService {
	
	MaturityDto save(MaturityDto dto);

	MaturityDto findById(Long id);
}
