package com.springpj.heroesapigateway.model.user;

import java.io.Serial;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springpj.heroesapigateway.dto.UserDto;

public class UserPrincipal implements UserDetails {
    @Serial
    private static final long serialVersionUID = 4889114430550460076L;
	private final UserDto user;

    public UserPrincipal(UserDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	
        return Collections.emptySet();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getAccountStatus().isExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getAccountStatus().isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.isCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getAccountStatus().isActive();
    }

	public UserDto getUser() {
		return user;
	}
    
    
}
