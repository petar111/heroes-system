package com.springpj.heroesentityservice.model.battlecapacity;

import java.math.BigInteger;

public class BattleCapacityDto {

    private Long id;

    private Long battleTypeId;

    private BigInteger attackMin;
    private BigInteger attackMax;

    private BigInteger defenceMin;
    private BigInteger defenceMax;


    private Long entityId;

    public Long getBattleTypeId() {
        return battleTypeId;
    }

    public void setBattleTypeId(Long battleTypeId) {
        this.battleTypeId = battleTypeId;
    }


    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public BigInteger getAttackMin() {
        return attackMin;
    }

    public void setAttackMin(BigInteger attackMin) {
        this.attackMin = attackMin;
    }

    public BigInteger getAttackMax() {
        return attackMax;
    }

    public void setAttackMax(BigInteger attackMax) {
        this.attackMax = attackMax;
    }

    public BigInteger getDefenceMin() {
        return defenceMin;
    }

    public void setDefenceMin(BigInteger defenceMin) {
        this.defenceMin = defenceMin;
    }

    public BigInteger getDefenceMax() {
        return defenceMax;
    }

    public void setDefenceMax(BigInteger defenceMax) {
        this.defenceMax = defenceMax;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

