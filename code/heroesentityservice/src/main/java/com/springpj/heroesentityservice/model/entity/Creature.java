package com.springpj.heroesentityservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "ENTITY_DEFINITION_ID")
public class Creature extends EntityDefinition{
}
