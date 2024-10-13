package com.springpj.heroesentityservice.repository;

import com.springpj.heroesentityservice.model.entity.Creature;
import com.springpj.heroesentityservice.model.entity.EntityDefinition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreatureRepository extends JpaRepository<Creature, Long> {
}
