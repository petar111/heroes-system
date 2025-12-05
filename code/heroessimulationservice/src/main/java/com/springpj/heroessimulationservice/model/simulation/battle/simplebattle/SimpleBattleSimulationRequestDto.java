package com.springpj.heroessimulationservice.model.simulation.battle.simplebattle;

import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationRequestDto;

public class SimpleBattleSimulationRequestDto extends BattleSimulationRequestDto {
    private Long entity1Id;
    private Long entity1Amount;

    private Long entity2Id;
    private Long entity2Amount;

    public Long getEntity1Id() {
        return entity1Id;
    }

    public void setEntity1Id(Long entity1Id) {
        this.entity1Id = entity1Id;
    }

    public Long getEntity2Id() {
        return entity2Id;
    }

    public void setEntity2Id(Long entity2Id) {
        this.entity2Id = entity2Id;
    }

    public Long getEntity1Amount() {
        return entity1Amount;
    }

    public void setEntity1Amount(Long entity1Amount) {
        this.entity1Amount = entity1Amount;
    }

    public Long getEntity2Amount() {
        return entity2Amount;
    }

    public void setEntity2Amount(Long entity2Amount) {
        this.entity2Amount = entity2Amount;
    }
}
