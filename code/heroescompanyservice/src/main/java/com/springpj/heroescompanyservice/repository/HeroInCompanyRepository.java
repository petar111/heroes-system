package com.springpj.heroescompanyservice.repository;

import com.springpj.heroescompanyservice.model.company.CreatureInCompany;
import com.springpj.heroescompanyservice.model.company.HeroInCompany;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HeroInCompanyRepository extends JpaRepository<HeroInCompany, Long> {
}
