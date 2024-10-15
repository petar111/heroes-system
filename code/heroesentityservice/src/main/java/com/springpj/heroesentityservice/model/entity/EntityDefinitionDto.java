package com.springpj.heroesentityservice.model.entity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.springpj.heroesentityservice.model.battlecapacity.BattleCapacityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EntityDefinitionDto {

	private Long id;
	@Size(max = 255, message = "Maximum name size is 255.")
	@NotBlank(message = "Name must not be blank.")
	private String name;
	@Size(max = 255, message = "Maximum description size is 255.")
	private String description;
	private Date dateCreated;
	private Date dateLastUpdated;
	private BigInteger hitpoints;
	private List<BattleCapacityDto> battleCapacities;
	private BigInteger experience;
	private Long originId;

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

	public BigInteger getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(BigInteger hitpoints) {
		this.hitpoints = hitpoints;
	}

	public BigInteger getExperience() {
		return experience;
	}

	public void setExperience(BigInteger experience) {
		this.experience = experience;
	}

	public List<BattleCapacityDto> getBattleCapacities() {
		return battleCapacities;
	}

	public void setBattleCapacities(List<BattleCapacityDto> battleCapacities) {
		this.battleCapacities = battleCapacities;
	}

	public Long getOriginId() {
		return originId;
	}

	public void setOriginId(Long originId) {
		this.originId = originId;
	}
}
