package com.springpj.heroesuserservice.model.authorization;

import jakarta.persistence.*;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RESOURCE_ID", nullable = false)
	private Long resourceId;
	@Column(name = "ACCESS_TYPE_ID", nullable = false)
	private Long accessTypeId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getResourceId() {
		return resourceId;
	}
	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}
	public Long getAccessTypeId() {
		return accessTypeId;
	}
	public void setAccessTypeId(Long accessTypeId) {
		this.accessTypeId = accessTypeId;
	}
	
	
	

}
