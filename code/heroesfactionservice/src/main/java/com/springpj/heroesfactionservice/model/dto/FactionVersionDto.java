package com.springpj.heroesfactionservice.model.dto;

import java.util.Date;

public class FactionVersionDto {
	
	private Long id;
	
	private String name;
	private String description;

	private FactionDto faction;
	
	private Date dateCreated;

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

	public FactionDto getFaction() {
		return faction;
	}

	public void setFaction(FactionDto faction) {
		this.faction = faction;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	

}
