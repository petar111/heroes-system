package com.springpj.heroesbattletypeservice.model.battletype;


import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "BATTLE_CAPACITY")
public class BattleCapacity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ATTACK")
    private BigInteger attack;
    @Column(name = "DEFENCE")
    private BigInteger defence;
    @Column(name = "BATTLE_TYPE_ID")
    private BigInteger battleTypeId;
    @Column(name = "ENTITY_ID")
    private BigInteger entityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
