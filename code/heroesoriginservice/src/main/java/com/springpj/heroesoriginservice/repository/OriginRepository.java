package com.springpj.heroesoriginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroesoriginservice.model.origin.Origin;

public interface OriginRepository extends JpaRepository<Origin, Long> {
}
