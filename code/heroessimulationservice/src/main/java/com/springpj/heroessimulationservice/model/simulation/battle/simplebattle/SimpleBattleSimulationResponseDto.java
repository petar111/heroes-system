package com.springpj.heroessimulationservice.model.simulation.battle.simplebattle;

import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;

public class SimpleBattleSimulationResponseDto extends BattleSimulationResponseDto {
    private SimpleBattleSimulationRequestDto request;

    private Long entityWinnerId;
    private String battleLog;

    public SimpleBattleSimulationRequestDto getRequest() {
        return request;
    }

    public void setRequest(SimpleBattleSimulationRequestDto request) {
        this.request = request;
    }

    public Long getEntityWinnerId() {
        return entityWinnerId;
    }

    public void setEntityWinnerId(Long entityWinnerId) {
        this.entityWinnerId = entityWinnerId;
    }

    public String getBattleLog() {
        return battleLog;
    }

    public void setBattleLog(String battleLog) {
        this.battleLog = battleLog;
    }
}
