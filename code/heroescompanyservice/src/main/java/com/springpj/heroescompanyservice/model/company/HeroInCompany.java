package com.springpj.heroescompanyservice.model.company;

import jakarta.persistence.*;

@Entity
@Table(name = "HERO_IN_COMPANY")
public class HeroInCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "HERO_ID")
    private Long heroId;
    @Column(name = "COMPANY_ID")
    private Long companyId;

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}
