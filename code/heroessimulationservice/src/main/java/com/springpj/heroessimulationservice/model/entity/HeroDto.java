package com.springpj.heroessimulationservice.model.entity;

import java.math.BigInteger;

public class HeroDto extends EntityDefinitionDto {
    private BigInteger experience;


    public BigInteger getExperience() {
        return experience;
    }
    public void setExperience(BigInteger experience) {
        this.experience = experience;
    }
}
