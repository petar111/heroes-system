package com.springpj.heroesentityservice.model.dto;

import jakarta.persistence.*;

import java.math.BigInteger;


public class LevelDto {


    private Long id;
    private Long number;
    private BigInteger experienceLower;
    private BigInteger experienceUpper;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigInteger getExperienceLower() {
        return experienceLower;
    }

    public void setExperienceLower(BigInteger experienceLower) {
        this.experienceLower = experienceLower;
    }

    public BigInteger getExperienceUpper() {
        return experienceUpper;
    }

    public void setExperienceUpper(BigInteger experienceUpper) {
        this.experienceUpper = experienceUpper;
    }
}
