package com.springpj.heroesuserservice.repository;


import com.springpj.heroesuserservice.model.authorization.AccessType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessTypeRepository extends JpaRepository<AccessType, Long> {

}
