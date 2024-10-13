package com.springpj.heroesbattletypeservice.model.dto;

import java.math.BigInteger;

public class BattleCapacityDto {

    private Long battleTypeId;
    private BigInteger attack;
    private BigInteger defence;

    private Long entityId;

    public Long getBattleTypeId() {
        return battleTypeId;
    }

    public void setBattleTypeId(Long battleTypeId) {
        this.battleTypeId = battleTypeId;
    }

    public BigInteger getAttack() {
        return attack;
    }

    public void setAttack(BigInteger attack) {
        this.attack = attack;
    }

    public BigInteger getDefence() {
        return defence;
    }

    public void setDefence(BigInteger defence) {
        this.defence = defence;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}
