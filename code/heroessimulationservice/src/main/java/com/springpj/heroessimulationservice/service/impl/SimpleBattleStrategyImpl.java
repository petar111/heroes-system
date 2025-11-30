package com.springpj.heroessimulationservice.service.impl;

import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.simulation.battle.*;
import com.springpj.heroessimulationservice.service.BattleStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimpleBattleStrategyImpl implements BattleStrategy {

    Random randomizer = new Random();

    private final SimpleBattleSimulationComponent request;
    private StringBuilder log = new StringBuilder();
    public SimpleBattleStrategyImpl(SimpleBattleSimulationComponent request) {
        this.request = request;
    }

    @Override
    public BattleSimulationResponseDto handleBattle() {

        Random random = new Random();

        log = new StringBuilder();

        Combatant combatant1 = new Combatant();
        combatant1.setEntity(request.entity1());
        combatant1.setHitpoints(request.entity1().getHitpoints().longValue());

        Combatant combatant2 = new Combatant();
        combatant2.setEntity(request.entity2());
        combatant2.setHitpoints(request.entity2().getHitpoints().longValue());


        List<Combatant> opponents = new java.util.ArrayList<>(List.of(combatant1, combatant2));

        Collections.shuffle(opponents);

        Combatant firstCombatant = opponents.get(0);
        Combatant secondCombatant = opponents.get(1);


        log.append(String.format("INFO: ============ Starting battle between %s and %s ================\n",firstCombatant.getEntity().getName(), secondCombatant.getEntity().getName()));
        log.append(String.format("INFO: First combatant: %s\n",firstCombatant.getEntity().getName()));
        log.append(String.format("INFO: Second combatant: %s\n",secondCombatant.getEntity().getName()));


        boolean firstCombatantTurn = true;
        int turnNumber = 1;
        while(firstCombatant.getHitpoints() > 0 && secondCombatant.getHitpoints() > 0 && turnNumber <= 1000){

            Combatant attacker = firstCombatantTurn ? firstCombatant : secondCombatant;
            Combatant defender = firstCombatantTurn ? secondCombatant : firstCombatant;

            log.append(String.format("INFO: Turn number: %s - START\n", turnNumber));
            log.append(String.format("INFO: Attacker: %s, Defender: %s\n", attacker.getEntity().getName(), defender.getEntity().getName()));


            CalculatedBattleCapacities battleCapacities = calculateBattleCapacities(attacker, defender);

            long attackPower = random.nextLong(battleCapacities.getAttackerBattleCapacity().getAttackMin().longValue(), battleCapacities.getAttackerBattleCapacity().getAttackMax().longValue() + 1);
            long defencePower = random.nextLong(battleCapacities.getDefenderBattleCapacity().getDefenceMin().longValue(), battleCapacities.getDefenderBattleCapacity().getDefenceMax().longValue() + 1);

            log.append(String.format("INFO: Attacker max damage: %s,min damage: %s \n", battleCapacities.getAttackerBattleCapacity().getAttackMax(), battleCapacities.getAttackerBattleCapacity().getAttackMin()));
            log.append(String.format("INFO: Defender max defend: %s,min defend: %s \n", battleCapacities.getDefenderBattleCapacity().getDefenceMax(), battleCapacities.getDefenderBattleCapacity().getDefenceMin()));

            log.append(String.format("INFO: Evaluated attack: %s\n", attackPower));
            log.append(String.format("INFO: Evaluated defend: %s\n", defencePower));

            long difference = Math.max(attackPower - defencePower, 1);

            log.append(String.format("INFO: Attacker deals %s damage\n", difference));
            defender.setHitpoints(defender.getHitpoints() - difference);

            log.append(String.format("INFO: Attacker %s - remaining hitpoints %s\n", attacker.getEntity().getName(), attacker.getHitpoints()));
            log.append(String.format("INFO: Defender %s - remaining hitpoints %s\n", defender.getEntity().getName(), defender.getHitpoints()));

            log.append(String.format("INFO: Turn number: %s - END\n", turnNumber));

            turnNumber++;
            firstCombatantTurn = !firstCombatantTurn;
        }



        Combatant winner = firstCombatant.getHitpoints() > secondCombatant.getHitpoints() ? firstCombatant : secondCombatant;
        Combatant loser = firstCombatant.getHitpoints() <= secondCombatant.getHitpoints() ? firstCombatant : secondCombatant;

        log.append("Battle finished.\n");
        log.append(String.format("INFO: Winner: %s, Loser: %s\n", winner.getEntity().getName(), loser.getEntity().getName()));
        log.append(String.format("INFO: Winner remaining hitpoints %s\n", winner.getHitpoints()));
        log.append(String.format("INFO: Loser remaining hitpoints %s\n", loser.getHitpoints()));

        log.append("===============================================================================");

        SimpleBattleSimulationResponseDto response = new SimpleBattleSimulationResponseDto();
//        response.setRequest(request);
        response.setEntityWinnerId(winner.getEntity().getId());
        response.setBattleLog(log.toString());

        return response;
    }

    private CalculatedBattleCapacities calculateBattleCapacities(Combatant attacker, Combatant defender) {


        double maxAverage = 0;
        CalculatedBattleCapacities result = null;

        for(BattleCapacityDto attackerCapacity : attacker.getEntity().getBattleCapacities()){
            BattleCapacityDto defenderCapacity = defender.getEntity().getBattleCapacities()
                    .stream().filter(dbc -> dbc.getBattleTypeId().equals(attackerCapacity.getBattleTypeId()))
                    .findAny().orElseThrow();

            double currentAverage = simulateAttackAgainstDefend(attackerCapacity, defenderCapacity);

            if(maxAverage < currentAverage){
                maxAverage = currentAverage;
                result = new CalculatedBattleCapacities(attackerCapacity, defenderCapacity);
            }
        }

        log.append(String.format("DEBUG: Attack chosen with average damage %s.\n", maxAverage));

        return result;
    }

    private double simulateAttackAgainstDefend(BattleCapacityDto attackCapacity, BattleCapacityDto defenderCapacity) {
        Random random = new Random();

        long sum = 0;

        for(int i = 1; i <= 10; i++){
            long attackPower = random.nextLong(attackCapacity.getAttackMin().longValue(), attackCapacity.getAttackMax().longValue() + 1);
            long defencePower = random.nextLong(defenderCapacity.getDefenceMin().longValue(), defenderCapacity.getDefenceMax().longValue() + 1);

            long difference = Math.max(attackPower - defencePower, 1);

            sum += difference;
        }

        return (double) sum / 10;


    }
}
