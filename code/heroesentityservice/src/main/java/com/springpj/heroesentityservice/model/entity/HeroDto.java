package com.springpj.heroesentityservice.model.entity;

import com.springpj.heroesentityservice.model.level.LevelDto;

import java.math.BigInteger;

public class HeroDto extends EntityDefinitionDto {
    private BigInteger experience;

    private LevelDto level;

    public BigInteger getExperience() {
        return experience;
    }
    public void setExperience(BigInteger experience) {
        this.experience = experience;
    }

    public LevelDto getLevel() {
        return level;
    }

    public void setLevel(LevelDto level) {
        this.level = level;
    }
}
