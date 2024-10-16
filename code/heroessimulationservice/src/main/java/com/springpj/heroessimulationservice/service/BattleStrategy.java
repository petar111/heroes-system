package com.springpj.heroessimulationservice.service;

import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationRequestDto;
import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;

public interface BattleStrategy {
    BattleSimulationResponseDto handleBattle();
}
