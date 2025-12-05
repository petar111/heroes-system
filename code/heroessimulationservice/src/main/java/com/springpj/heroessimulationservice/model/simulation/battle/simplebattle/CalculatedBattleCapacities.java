package com.springpj.heroessimulationservice.model.simulation.battle.simplebattle;

import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;

public class CalculatedBattleCapacities {

    private final BattleCapacityDto attackerBattleCapacity;
    private final BattleCapacityDto defenderBattleCapacity;


    public CalculatedBattleCapacities(BattleCapacityDto attackerBattleCapacity, BattleCapacityDto defenderBattleCapacity) {
        this.attackerBattleCapacity = attackerBattleCapacity;
        this.defenderBattleCapacity = defenderBattleCapacity;
    }

    public BattleCapacityDto getAttackerBattleCapacity() {
        return attackerBattleCapacity;
    }

    public BattleCapacityDto getDefenderBattleCapacity() {
        return defenderBattleCapacity;
    }
}
