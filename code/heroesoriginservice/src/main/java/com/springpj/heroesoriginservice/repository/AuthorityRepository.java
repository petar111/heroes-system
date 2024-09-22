package com.springpj.heroesoriginservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springpj.heroesoriginservice.model.authorization.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
