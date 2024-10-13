package com.springpj.heroescompanyservice.model.dto;

import java.math.BigInteger;

public class CreatureInCompanyDto {
    private Long creatureId;
    private Long companyId;
    private BigInteger amount;

    public Long getCreatureId() {
        return creatureId;
    }

    public void setCreatureId(Long creatureId) {
        this.creatureId = creatureId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }
}
