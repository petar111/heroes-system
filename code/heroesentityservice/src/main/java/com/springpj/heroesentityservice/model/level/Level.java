package com.springpj.heroesentityservice.model.level;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "_LEVEL")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NUMBER", nullable = false, unique = true)
    private Long number;
    @Column(name = "EXPERIENCE_LOWER")
    private BigInteger experienceLower;
    @Column(name = "EXPERIENCE_UPPER")
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
