package com.springpj.heroessimulationservice.service.impl;

import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.simulation.battle.*;
import com.springpj.heroessimulationservice.service.BattleStrategy;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimpleBattleStrategyImpl implements BattleStrategy {

    Random randomizer = new Random();

    private final SimpleBattleSimulationRequestDto request;
    public SimpleBattleStrategyImpl(SimpleBattleSimulationRequestDto request) {
        this.request = request;
    }

    @Override
    public BattleSimulationResponseDto handleBattle() {

        Random random = new Random();

        Combatant combatant1 = new Combatant();
        combatant1.setEntity(request.getEntity1());
        combatant1.setHitpoints(request.getEntity1().getHitpoints().longValue());
        combatant1.setAttackingCapacity(request.getEntity1().getBattleCapacities().stream().filter(c -> c.getId().equals(request.getBattleTypeIdForEntity1()))
                .findAny().orElseThrow(() -> new RuntimeException("FATAL: Battle type for entity1 with id not found: " + request.getBattleTypeIdForEntity1())));

        Combatant combatant2 = new Combatant();
        combatant2.setEntity(request.getEntity2());
        combatant2.setHitpoints(request.getEntity2().getHitpoints().longValue());
        combatant2.setAttackingCapacity(request.getEntity2().getBattleCapacities().stream().filter(c -> c.getId().equals(request.getBattleTypeIdForEntity2()))
                .findAny().orElseThrow(() -> new RuntimeException("FATAL: Battle type for entity2 with id not found: " + request.getBattleTypeIdForEntity2())));

        List<Combatant> opponents = new java.util.ArrayList<>(List.of(combatant1, combatant2));

        Collections.shuffle(opponents);

        Combatant firstCombatant = opponents.get(0);
        Combatant secondCombatant = opponents.get(1);

        StringBuilder log = new StringBuilder("");
        log.append(String.format("============ Starting battle between %s and %s ================\n",firstCombatant.getEntity().getName(), secondCombatant.getEntity().getName()));
        log.append(String.format("First combatant: %s",firstCombatant.getEntity().getName()));
        log.append(String.format("Second combatant: %s",secondCombatant.getEntity().getName()));


        boolean firstCombatantTurn = true;
        int turnNumber = 1;
        while(firstCombatant.getHitpoints() > 0 && secondCombatant.getHitpoints() > 0){

            Combatant attacker = firstCombatantTurn ? firstCombatant : secondCombatant;
            Combatant defender = firstCombatantTurn ? secondCombatant : firstCombatant;

            log.append(String.format("Turn number: %s - START\n", turnNumber));
            log.append(String.format("Attacker: %s, Defender: %s\n", attacker.getEntity().getName(), defender.getEntity().getName()));

            BattleCapacityDto defenderBattleCapacity = defender.getEntity().getBattleCapacities()
                    .stream().filter(c -> c.getBattleTypeId().equals(attacker.getAttackingCapacity().getBattleTypeId())).findAny()
                    .orElseThrow(() -> new RuntimeException("FATAL: Defender battleCapacity not found with battle capacity id: " + attacker.getAttackingCapacity().getBattleTypeId()));

            long attackPower = random.nextLong(attacker.getAttackingCapacity().getAttackMin().longValue(), attacker.getAttackingCapacity().getAttackMax().longValue() + 1);
            long defencePower = random.nextLong(defenderBattleCapacity.getDefenceMin().longValue(), defenderBattleCapacity.getDefenceMax().longValue() + 1);

            log.append(String.format("Attacker max damage: %s,min damage: %s \n", attacker.getAttackingCapacity().getAttackMax(), attacker.getAttackingCapacity().getAttackMin()));
            log.append(String.format("Defender max defend: %s,min defend: %s \n", defenderBattleCapacity.getDefenceMax(), defenderBattleCapacity.getDefenceMin()));

            log.append(String.format("Evaluated attack: %s\n", attackPower));
            log.append(String.format("Evaluated defend: %s\n", defencePower));

            if(attackPower - defencePower > 0){
                long diffenence = attackPower - defencePower;
                log.append(String.format("Attacker deals %s damage\n", diffenence));
                defender.setHitpoints(defender.getHitpoints() - diffenence);
            }

            log.append(String.format("Turn number: %s - END\n", turnNumber));

            turnNumber++;
            firstCombatantTurn = !firstCombatantTurn;
        }


        Combatant winner = firstCombatant.getHitpoints() > 0 ? firstCombatant : secondCombatant;
        Combatant loser = firstCombatant.getHitpoints() > 0 ? secondCombatant : firstCombatant;

        log.append(String.format("Battle finished. Winner: %s, Loser: %s\n", winner.getEntity().getName(), loser.getEntity().getName()));
        log.append(String.format("Winner remaining hitpoints %s\n", winner.getHitpoints()));

        log.append("===============================================================================");

        SimpleBattleSimulationResponseDto response = new SimpleBattleSimulationResponseDto();
        response.setRequest(request);
        response.setEntityWinnerId(winner.getEntity().getId());
        response.setBattleLog(log.toString());

        return response;
    }
}
