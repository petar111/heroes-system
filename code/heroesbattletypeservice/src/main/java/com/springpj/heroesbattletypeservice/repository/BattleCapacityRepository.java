package com.springpj.heroesbattletypeservice.repository;

import com.springpj.heroesbattletypeservice.model.battlecapacity.BattleCapacity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BattleCapacityRepository extends JpaRepository<BattleCapacity, Long> {
    List<BattleCapacity> findAllByEntityId(Long id);
}
