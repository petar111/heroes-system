package com.springpj.heroessimulationservice.service.impl;

import com.springpj.heroessimulationservice.client.BattleTypeClientProxy;
import com.springpj.heroessimulationservice.client.EntityClientProxy;
import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationRequestDto;
import com.springpj.heroessimulationservice.model.simulation.battle.SimpleBattleSimulationResponseDto;
import com.springpj.heroessimulationservice.service.BattleStrategy;
import com.springpj.heroessimulationservice.service.SimulationService;
import com.springpj.heroessimulationservice.service.factory.BattleSimulationFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SimulationServiceImpl implements SimulationService {

    private final BattleTypeClientProxy battleTypeClientProxy;
    private final EntityClientProxy entityClientProxy;


    public SimulationServiceImpl(BattleTypeClientProxy battleTypeClientProxy, EntityClientProxy entityClientProxy) {
        this.battleTypeClientProxy = battleTypeClientProxy;
        this.entityClientProxy = entityClientProxy;
    }

    @Override
    public Mono<BattleSimulationResponseDto> handleSimpleBattle(SimpleBattleSimulationRequestDto request) {

        EntityDefinitionDto entity1 = request.getEntity1();
        EntityDefinitionDto entity2 = request.getEntity2();

        Mono<EntityDefinitionDto> capacityForEntity1 = battleTypeClientProxy.getCapacitiesByEntityId(entity1.getId()).map(c -> {
            entity1.setBattleCapacities(c);
            return entity1;
        });
        Mono<EntityDefinitionDto> capacityForEntity2 = battleTypeClientProxy.getCapacitiesByEntityId(entity2.getId()).map(c -> {
            entity2.setBattleCapacities(c);
            return entity2;
        });;

        return Mono.zip(capacityForEntity1, capacityForEntity2).map(t -> BattleSimulationFactory.createSimpleBattleSimulation(request).handleBattle());
    }
}
