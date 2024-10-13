package com.springpj.heroesentityservice.model.entity;

import com.springpj.heroesentityservice.model.level.Level;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@PrimaryKeyJoinColumn(name = "ENTITY_DEFINITION_ID")
public class Hero extends EntityDefinition {

    @Column(name = "EXPERIENCE")
    private BigInteger experience;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID", referencedColumnName = "ID")
    private Level level;


    public BigInteger getExperience() {
        return experience;
    }
    public void setExperience(BigInteger experience) {
        this.experience = experience;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
