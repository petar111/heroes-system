package com.springpj.heroessimulationservice.service.impl;

import com.springpj.heroessimulationservice.client.EntityClientProxy;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.simulation.battle.BattleSimulationResponseDto;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.OneOnOneCombatant;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationComponent;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationRequestDto;
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
    public Mono<BattleSimulationResponseDto> handleOneOnOneBattle(SimpleBattleSimulationRequestDto request) {

        Mono<EntityDefinitionDto> entity1 = entityClientProxy.findEntityById(request.getEntity1Id());
        Mono<EntityDefinitionDto> entity2 = entityClientProxy.findEntityById(request.getEntity2Id());

        return Mono.zip(entity1, entity2)
                .map(t -> BattleSimulationFactory.createSimpleBattleSimulation(
                        composeBattleSimulationComponent(t.getT1(), t.getT2(), request)).handleBattle());
    }

    private SimpleBattleSimulationComponent composeBattleSimulationComponent(EntityDefinitionDto entity1, EntityDefinitionDto entity2, SimpleBattleSimulationRequestDto request) {

        OneOnOneCombatant combatant1 = new OneOnOneCombatant();
        combatant1.setEntity(entity1);
        combatant1.setCurrentHitpoints(entity1.getHitpoints().longValue());
        combatant1.setAmount(request.getEntity1Amount());

        OneOnOneCombatant combatant2 = new OneOnOneCombatant();
        combatant2.setEntity(entity2);
        combatant2.setCurrentHitpoints(entity2.getHitpoints().longValue());
        combatant2.setAmount(request.getEntity2Amount());

        return new SimpleBattleSimulationComponent(combatant1, combatant2);
    }

}
