package com.springpj.heroestraitservice.repository;

import com.springpj.heroestraitservice.model.trait.Trait;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TraitRepository extends JpaRepository<Trait, Long> {
}
