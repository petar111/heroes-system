package com.springpj.heroescompanyservice.repository;

import com.springpj.heroescompanyservice.model.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {
}
