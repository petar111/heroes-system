package com.springpj.heroesfactionservice.repository;

import com.springpj.heroesfactionservice.model.faction.Faction;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FactionRepository extends JpaRepository<Faction, Long> {
}
