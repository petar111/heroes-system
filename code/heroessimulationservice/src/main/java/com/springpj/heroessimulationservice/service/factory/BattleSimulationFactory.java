package com.springpj.heroessimulationservice.service.factory;

import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationComponent;
import com.springpj.heroessimulationservice.service.impl.SimpleBattleStrategyImpl;

public class BattleSimulationFactory {
    public static SimpleBattleStrategyImpl createSimpleBattleSimulation(SimpleBattleSimulationComponent request) {
        return new SimpleBattleStrategyImpl(request);
    }
}
