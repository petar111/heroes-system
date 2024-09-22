package com.springpj.heroesuserservice.repository;

import com.springpj.heroesuserservice.model.authorization.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String name);
}
