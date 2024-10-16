package com.springpj.heroescompanyservice.model.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CompanyDto {

	private Long id;

	@Size(max = 255, message = "Maximum name size is 255.")
	@NotBlank(message = "Name must not be blank.")
	private String name;
	@Size(max = 255, message = "Maximum description size is 255.")
	private String description;
	
	private Date dateCreated;
	private Date dateLastUpdated;

	private Long factionId;

	private Long leadHeroId;

	private List<CreatureInCompanyDto> creaturesInCompany;
	private List<HeroInCompanyDto> heroesInCompany;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}
	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	public Long getFactionId() {
		return factionId;
	}
	public void setFactionId(Long factionId) {
		this.factionId = factionId;
	}

	public Long getLeadHeroId() {
		return leadHeroId;
	}

	public void setLeadHeroId(Long leadHeroId) {
		this.leadHeroId = leadHeroId;
	}

	public List<CreatureInCompanyDto> getCreaturesInCompany() {
		return creaturesInCompany;
	}

	public void setCreaturesInCompany(List<CreatureInCompanyDto> creaturesInCompany) {
		this.creaturesInCompany = creaturesInCompany;
	}

	public List<HeroInCompanyDto> getHeroesInCompany() {
		return heroesInCompany;
	}

	public void setHeroesInCompany(List<HeroInCompanyDto> heroesInCompany) {
		this.heroesInCompany = heroesInCompany;
	}
}
