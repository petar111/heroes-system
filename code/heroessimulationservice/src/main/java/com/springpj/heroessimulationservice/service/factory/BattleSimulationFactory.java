package com.springpj.heroessimulationservice.service.factory;

import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationRequestDto;
import com.springpj.heroessimulationservice.service.impl.SimpleBattleStrategyImpl;

public class BattleSimulationFactory {
    public static SimpleBattleStrategyImpl createSimpleBattleSimulation(SimpleBattleSimulationRequestDto request) {
        return new SimpleBattleStrategyImpl(request);
    }
}
