package com.springpj.heroesoriginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroesoriginservice.model.authorization.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
