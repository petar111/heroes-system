package com.springpj.heroessimulationservice.model.simulation.battle;

import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;

public class SimpleBattleSimulationRequestDto extends BattleSimulationRequestDto {
    private EntityDefinitionDto entity1;
    private EntityDefinitionDto entity2;

    private Long battleTypeIdForEntity1;
    private Long battleTypeIdForEntity2;

    public EntityDefinitionDto getEntity1() {
        return entity1;
    }

    public void setEntity1(EntityDefinitionDto entity1) {
        this.entity1 = entity1;
    }

    public EntityDefinitionDto getEntity2() {
        return entity2;
    }

    public void setEntity2(EntityDefinitionDto entity2) {
        this.entity2 = entity2;
    }

    public Long getBattleTypeIdForEntity1() {
        return battleTypeIdForEntity1;
    }

    public void setBattleTypeIdForEntity1(Long battleTypeIdForEntity1) {
        this.battleTypeIdForEntity1 = battleTypeIdForEntity1;
    }

    public Long getBattleTypeIdForEntity2() {
        return battleTypeIdForEntity2;
    }

    public void setBattleTypeIdForEntity2(Long battleTypeIdForEntity2) {
        this.battleTypeIdForEntity2 = battleTypeIdForEntity2;
    }
}
