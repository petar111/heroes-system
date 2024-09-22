package com.springpj.heroesuserservice.repository;


import com.springpj.heroesuserservice.model.authorization.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
