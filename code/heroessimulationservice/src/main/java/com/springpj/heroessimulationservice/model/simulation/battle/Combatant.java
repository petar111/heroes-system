package com.springpj.heroessimulationservice.model.simulation.battle;

import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;

public class Combatant {
    private EntityDefinitionDto entity;
    private BattleCapacityDto attackingCapacity;

    private long hitpoints;

    public EntityDefinitionDto getEntity() {
        return entity;
    }

    public void setEntity(EntityDefinitionDto entity) {
        this.entity = entity;
    }

    public BattleCapacityDto getAttackingCapacity() {
        return attackingCapacity;
    }

    public void setAttackingCapacity(BattleCapacityDto attackingCapacity) {
        this.attackingCapacity = attackingCapacity;
    }

    public long getHitpoints() {
        return hitpoints;
    }

    public void setHitpoints(long hitpoints) {
        this.hitpoints = hitpoints;
    }
}
