package com.springpj.heroescompanyservice.repository;

import com.springpj.heroescompanyservice.model.company.Company;
import com.springpj.heroescompanyservice.model.company.CreatureInCompany;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreatureInCompanyRepository extends JpaRepository<CreatureInCompany, Long> {
}
