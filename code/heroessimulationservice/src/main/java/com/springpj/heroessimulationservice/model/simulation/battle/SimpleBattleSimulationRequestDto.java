package com.springpj.heroessimulationservice.model.simulation.battle;

import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;

public class SimpleBattleSimulationRequestDto extends BattleSimulationRequestDto {
    private Long entity1Id;
    private Long entity2Id;

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
}
