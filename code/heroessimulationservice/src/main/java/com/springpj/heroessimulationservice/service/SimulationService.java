package com.springpj.heroessimulationservice.service;

import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationRequestDto;
import reactor.core.publisher.Mono;

public interface SimulationService {
    Mono<BattleSimulationResponseDto> handleOneOnOneBattle(SimpleBattleSimulationRequestDto request);
}
