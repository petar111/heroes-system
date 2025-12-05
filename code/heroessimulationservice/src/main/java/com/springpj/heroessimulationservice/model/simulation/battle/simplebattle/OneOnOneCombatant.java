package com.springpj.heroessimulationservice.model.simulation.battle.simplebattle;

import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.simulation.battle.Combatant;

public class OneOnOneCombatant implements Combatant {
    private EntityDefinitionDto entity;
    private BattleCapacityDto attackingCapacity;
    private long amount;

    private long currentHitpoints;

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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public long getTotalHitpoints(){
        if(amount == 0){
            return 0;
        }

        return (amount - 1) * entity.getHitpoints().longValue() + currentHitpoints;
    }

    public long getCurrentHitpoints() {
        return currentHitpoints;
    }

    public void setCurrentHitpoints(long currentHitpoints) {
        this.currentHitpoints = currentHitpoints;
    }


    @Override
    public void takeDamage(long damage){
        long totalHitpointsRemaining = getTotalHitpoints() - damage;

        if(totalHitpointsRemaining <= 0){
            setCurrentHitpoints(0);
            setAmount(0);
            return;
        }

        long remainingAmount = totalHitpointsRemaining / entity.getHitpoints().longValue() + 1;
        long remainingCurrentHitpoints = totalHitpointsRemaining % entity.getHitpoints().longValue();

        if(remainingCurrentHitpoints == 0){
            remainingAmount--;
            remainingCurrentHitpoints = entity.getHitpoints().longValue();
        }

        setAmount(remainingAmount);
        setCurrentHitpoints(remainingCurrentHitpoints);
    }
}
