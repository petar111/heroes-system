package com.springpj.heroesoriginservice.model.dto;

import com.springpj.heroesoriginservice.authorization.context.AuthorizationContext;

public class AuthorityDto {
	
	private final Long id;
	private final Long resourceId;
	private final Long accessTypeId;
	
	private final String authorityString;

	public AuthorityDto(Long id, Long resourceId, Long accessTypeId) {
		this.id = id;
		this.resourceId = resourceId;
		this.accessTypeId = accessTypeId;
		
		this.authorityString = 
				AuthorizationContext.INSTANCE.provideAuthorityString(resourceId, accessTypeId);
	}

	public Long getId() {
		return id;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public Long getAccessTypeId() {
		return accessTypeId;
	}

	public String getAuthorityString() {
		return authorityString;
	}

	
	

}
