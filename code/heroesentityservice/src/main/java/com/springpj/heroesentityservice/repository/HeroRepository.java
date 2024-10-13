package com.springpj.heroesentityservice.repository;

import com.springpj.heroesentityservice.model.entity.EntityDefinition;
import com.springpj.heroesentityservice.model.entity.Hero;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeroRepository extends JpaRepository<Hero, Long> {
}
