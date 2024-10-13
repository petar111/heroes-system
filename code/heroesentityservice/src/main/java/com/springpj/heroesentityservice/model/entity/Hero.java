package com.springpj.heroesentityservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.math.BigInteger;

@Entity
@PrimaryKeyJoinColumn(name = "ENTITY_DEFINITION_ID")
public class Hero extends EntityDefinition {

    @Column(name = "EXPERIENCE")
    private BigInteger experience;

    public BigInteger getExperience() {
        return experience;
    }
    public void setExperience(BigInteger experience) {
        this.experience = experience;
    }
}
