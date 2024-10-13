package com.springpj.heroescompanyservice.model.dto;

public class HeroInCompanyDto {
    private Long heroId;
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
