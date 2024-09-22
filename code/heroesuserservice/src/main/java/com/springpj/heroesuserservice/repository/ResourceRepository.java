package com.springpj.heroesuserservice.repository;

import com.springpj.heroesuserservice.model.authorization.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
