package com.springpj.heroessimulationservice.model.simulation.battle;

public interface Combatant {

    long getTotalHitpoints();
    void takeDamage(long damage);
}
