package com.springpj.heroesoriginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroesoriginservice.model.authorization.AccessType;

public interface AccessTypeRepository extends JpaRepository<AccessType, Long> {

}
