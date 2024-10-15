package com.springpj.heroestraitservice.repository;

import com.springpj.heroestraitservice.model.maturity.Maturity;
import com.springpj.heroestraitservice.model.trait.Trait;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MaturityRepository extends JpaRepository<Maturity, Long> {
}
