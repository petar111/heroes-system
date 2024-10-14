package com.springpj.heroesspellservice.repository;

import com.springpj.heroesspellservice.model.spell.Spell;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SpellRepository extends JpaRepository<Spell, Long> {
}
