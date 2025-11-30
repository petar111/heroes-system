package com.springpj.heroessimulationservice.service.impl;

import com.springpj.heroessimulationservice.client.EntityClientProxy;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationComponent;
import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationRequestDto;
import com.springpj.heroessimulationservice.service.SimulationService;
import com.springpj.heroessimulationservice.service.factory.BattleSimulationFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SimulationServiceImpl implements SimulationService {
    private final EntityClientProxy entityClientProxy;


    public SimulationServiceImpl(EntityClientProxy entityClientProxy) {
        this.entityClientProxy = entityClientProxy;
    }

    @Override
    public Mono<BattleSimulationResponseDto> handleSimpleBattle(SimpleBattleSimulationRequestDto request) {

        Mono<EntityDefinitionDto> entity1 = entityClientProxy.findEntityById(request.getEntity1Id());
        Mono<EntityDefinitionDto> entity2 = entityClientProxy.findEntityById(request.getEntity2Id());

        return Mono.zip(entity1, entity2)
                .map(t -> BattleSimulationFactory.createSimpleBattleSimulation(
                        new SimpleBattleSimulationComponent(t.getT1(), t.getT2())).handleBattle());
    }
}
