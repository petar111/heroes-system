package com.springpj.heroesuserservice.model.authorization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESOURCE")
public class Resource {

	@Id
	private Long id;
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "_TYPE", nullable = false, unique = true)
	private ResourceType resourceType;
	
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
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	
	
	
}
