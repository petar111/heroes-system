package com.springpj.heroesentityservice.repository;

import com.springpj.heroesentityservice.model.entity.EntityDefinition;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntityDefinitionRepository extends JpaRepository<EntityDefinition, Long> {
}
