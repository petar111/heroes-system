package com.springpj.heroesbattletypeservice.repository;

import com.springpj.heroesbattletypeservice.model.battletype.BattleCapacity;
import com.springpj.heroesbattletypeservice.model.battletype.BattleType;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BattleCapacityRepository extends JpaRepository<BattleCapacity, Long> {
}
