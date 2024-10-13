package com.springpj.heroesentityservice.repository;

import com.springpj.heroesentityservice.model.level.Level;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LevelRepository extends JpaRepository<Level, Long> {
}
