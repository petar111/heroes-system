package com.springpj.heroesbattletypeservice.model.battlecapacity;


import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "BATTLE_CAPACITY")
public class BattleCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BATTLE_TYPE_ID")
    private BigInteger battleTypeId;
    @Column(name = "ENTITY_ID")
    private BigInteger entityId;

    @Column(name = "ATTACK_MIN")
    private BigInteger attackMin;
    @Column(name = "ATTACK_MAX")
    private BigInteger attackMax;

    @Column(name = "DEFENCE_MIN")
    private BigInteger defenceMin;
    @Column(name = "DEFENCE_MAX")
    private BigInteger defenceMax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getBattleTypeId() {
        return battleTypeId;
    }

    public void setBattleTypeId(BigInteger battleTypeId) {
        this.battleTypeId = battleTypeId;
    }

    public BigInteger getEntityId() {
        return entityId;
    }

    public void setEntityId(BigInteger entityId) {
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
}
