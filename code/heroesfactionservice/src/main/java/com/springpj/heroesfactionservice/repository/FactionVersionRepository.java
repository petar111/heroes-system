package com.springpj.heroesfactionservice.repository;

import java.util.List;

import com.springpj.heroesfactionservice.model.faction.Faction;
import com.springpj.heroesfactionservice.model.version.FactionVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactionVersionRepository extends JpaRepository<FactionVersion, Long> {
	
	List<FactionVersion> findAllByFaction(Faction faction);
}
