package com.springpj.heroessimulationservice.service;

import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationRequestDto;
import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationResponseDto;
import reactor.core.publisher.Mono;

public interface SimulationService {
    Mono<BattleSimulationResponseDto> handleSimpleBattle(SimpleBattleSimulationRequestDto request);
}
