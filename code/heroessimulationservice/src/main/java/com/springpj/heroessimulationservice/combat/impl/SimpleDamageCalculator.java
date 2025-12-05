package com.springpj.heroessimulationservice.combat.impl;

import com.springpj.heroessimulationservice.combat.DamageCalculator;
import com.springpj.heroessimulationservice.logging.SimulationLogger;
import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroessimulationservice.model.simulation.battle.DamageData;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.CalculatedBattleCapacities;
import com.springpj.heroessimulationservice.model.simulation.battle.simplebattle.OneOnOneCombatant;

import java.util.Random;

public class SimpleDamageCalculator implements DamageCalculator {

    private final OneOnOneCombatant attacker;
    private final OneOnOneCombatant defender;

    private final SimulationLogger log = SimulationLogger.INSTANCE;

    private SimpleDamageCalculator(OneOnOneCombatant attacker, OneOnOneCombatant defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    public static SimpleDamageCalculator from(OneOnOneCombatant attacker, OneOnOneCombatant defender){
        return new SimpleDamageCalculator(attacker, defender);
    }

    private double calculateAttackIncreasedPercentage(){
        return (attacker.getAmount() - 1) * 0.1 + 1;
    }


    @Override
    public DamageData calculateDamage() {
        CalculatedBattleCapacities battleCapacities = calculateBattleCapacities(attacker, defender);

        log.info(String.format("Max damage: %s, Min damage %s", calculateAttackMax(battleCapacities.getAttackerBattleCapacity()), battleCapacities.getAttackerBattleCapacity().getAttackMin()));
        log.info(String.format("Defence %s", battleCapacities.getDefenderBattleCapacity().getDefenceMax()));

        long damage = performAttack(battleCapacities.getAttackerBattleCapacity(), battleCapacities.getDefenderBattleCapacity());

        return new DamageData(damage);
    }

    private CalculatedBattleCapacities calculateBattleCapacities(OneOnOneCombatant attacker, OneOnOneCombatant defender) {


        double maxAverage = 0;
        CalculatedBattleCapacities result = null;

        double attackIncreasePercentDueAmount = (attacker.getAmount() - 1) * 0.1;

        for(BattleCapacityDto attackerCapacity : attacker.getEntity().getBattleCapacities()){
            BattleCapacityDto defenderCapacity = defender.getEntity().getBattleCapacities()
                    .stream().filter(dbc -> dbc.getBattleTypeId().equals(attackerCapacity.getBattleTypeId()))
                    .findAny().orElseThrow();



            double currentAverage = simulateAttackAgainstDefend(attackerCapacity, defenderCapacity, attackIncreasePercentDueAmount);

            if(maxAverage < currentAverage){
                maxAverage = currentAverage;
                result = new CalculatedBattleCapacities(attackerCapacity, defenderCapacity);
            }
        }

        log.debug(String.format("Chosen attack with average damage %s", maxAverage));
        return result;
    }

    private double simulateAttackAgainstDefend(BattleCapacityDto attackCapacity, BattleCapacityDto defenderCapacity, double attackIncreasePercentDueAmount) {

        long sum = 0;

        for(int i = 1; i <= 10; i++){
            sum += performAttack(attackCapacity, defenderCapacity);
        }

        return (double) sum / 10;


    }

    private long performAttack(BattleCapacityDto attackCapacity, BattleCapacityDto defenderCapacity){

        Random random = new Random();


        long attackPower = random.nextLong(attackCapacity.getAttackMin().longValue(), calculateAttackMax(attackCapacity) + 1);
        long defencePower = defenderCapacity.getDefenceMax().longValue();

        return Math.max(attackPower - defencePower, 1);
    }

    private long calculateAttackMax(BattleCapacityDto attackCapacity) {
        return Math.round((double) attackCapacity.getAttackMax().longValue() * calculateAttackIncreasedPercentage());
    }


}
