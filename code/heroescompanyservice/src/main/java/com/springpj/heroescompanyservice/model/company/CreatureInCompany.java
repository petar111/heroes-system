package com.springpj.heroescompanyservice.model.company;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "CREATURE_IN_COMPANY")
public class CreatureInCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CREATURE_ID")
    private Long creatureId;
    @Column(name = "COMPANY_ID")
    private Long companyId;
    @Column(name = "AMOUNT")
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
