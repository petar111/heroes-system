package com.springpj.heroesoriginservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroesoriginservice.model.origin.Origin;
import com.springpj.heroesoriginservice.model.version.OriginVersion;

public interface OriginVersionRepository extends JpaRepository<OriginVersion, Long> {
	
	List<OriginVersion> findAllByOrigin(Origin origin);
}
