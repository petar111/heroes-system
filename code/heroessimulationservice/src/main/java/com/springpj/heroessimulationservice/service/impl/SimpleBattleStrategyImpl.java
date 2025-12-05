package com.springpj.heroessimulationservice.service.impl;

import com.springpj.heroessimulationservice.combat.DamageCalculator;
import com.springpj.heroessimulationservice.combat.impl.SimpleDamageCalculator;
import com.springpj.heroessimulationservice.logging.SimulationLogger;
import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.simulation.battle.*;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.CalculatedBattleCapacities;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.OneOnOneCombatant;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationComponent;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.SimpleBattleSimulationResponseDto;
import com.springpj.heroessimulationservice.service.BattleStrategy;
import reactivefeign.utils.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimpleBattleStrategyImpl implements BattleStrategy {

    private final SimpleBattleSimulationComponent request;
    private final SimulationLogger log = SimulationLogger.INSTANCE;
    public SimpleBattleStrategyImpl(SimpleBattleSimulationComponent request) {
        this.request = request;
    }

    @Override
    public BattleSimulationResponseDto handleBattle() {
        log.reset();

        OneOnOneCombatant combatant1 = request.combatant1();
        OneOnOneCombatant combatant2 = request.combatant2();


        List<OneOnOneCombatant> opponents = new java.util.ArrayList<>(List.of(combatant1, combatant2));

        Collections.shuffle(opponents);

        OneOnOneCombatant firstCombatant = opponents.get(0);
        OneOnOneCombatant secondCombatant = opponents.get(1);


        log.info(String.format("============ Starting battle between %s and %s ================",firstCombatant.getEntity().getName(), secondCombatant.getEntity().getName()));
        log.info(String.format("First combatant: %s, amount: %s, hit points: %s",firstCombatant.getEntity().getName(),
                firstCombatant.getAmount(), firstCombatant.getTotalHitpoints()));
        log.info(String.format("Second combatant: %s, amount: %s, hit points: %s",secondCombatant.getEntity().getName(),
                secondCombatant.getAmount(), secondCombatant.getTotalHitpoints()));


        boolean firstCombatantTurn = true;
        int turnNumber = 1;
        while(firstCombatant.getTotalHitpoints() > 0 && secondCombatant.getTotalHitpoints() > 0 && turnNumber <= 1000){

            OneOnOneCombatant attacker = firstCombatantTurn ? firstCombatant : secondCombatant;
            OneOnOneCombatant defender = firstCombatantTurn ? secondCombatant : firstCombatant;

            log.info(String.format("Turn number: %s - START", turnNumber));
            log.info(String.format("Attacker: %s, Defender: %s", attacker.getEntity().getName(), defender.getEntity().getName()));

            DamageData damage = SimpleDamageCalculator.from(attacker, defender).calculateDamage();

            defender.takeDamage(damage.damage());
            log.info(String.format("%s deals %s damage", attacker.getEntity().getName(), damage.damage()));


            log.info(String.format("%s - remaining hit points %s", attacker.getEntity().getName(), attacker.getTotalHitpoints()));
            log.info(String.format("%s - remaining amount %s", attacker.getEntity().getName(), attacker.getAmount()));

            log.info(String.format("%s - remaining hit points %s", defender.getEntity().getName(), defender.getTotalHitpoints()));
            log.info(String.format("%s - remaining amount %s", defender.getEntity().getName(), defender.getAmount()));


            log.info(String.format("Turn number: %s - END", turnNumber));

            turnNumber++;
            firstCombatantTurn = !firstCombatantTurn;
        }

        Pair<OneOnOneCombatant, OneOnOneCombatant> outcome = determineWinner(firstCombatant, secondCombatant);

        log.info("Battle finished.");
        log.info(String.format("Winner: %s, Loser: %s", outcome.left.getEntity().getName(), outcome.right.getEntity().getName()));
        log.info(String.format("Winner remaining hit points %s", outcome.left.getCurrentHitpoints()));
        log.info(String.format("Loser remaining hit points %s", outcome.right.getCurrentHitpoints()));

        log.info("===============================================================================");

        SimpleBattleSimulationResponseDto response = new SimpleBattleSimulationResponseDto();
//        response.setRequest(request);
        response.setEntityWinnerId(outcome.left.getEntity().getId());
        response.setBattleLog(log.dump());
        return response;
    }

    private Pair<OneOnOneCombatant, OneOnOneCombatant> determineWinner(OneOnOneCombatant firstCombatant, OneOnOneCombatant secondCombatant) {
        if(firstCombatant.getTotalHitpoints() > 0){
            return new Pair<>(firstCombatant, secondCombatant);
        } else {
            return new Pair<>(secondCombatant, firstCombatant);
        }
    }


}
