package com.springpj.heroesauthorizationserver.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springpj.heroesauthorizationserver.client.UserClientProxy;
import com.springpj.heroesauthorizationserver.model.user.UserPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserClientProxy userClient;

    public UserDetailsServiceImpl(UserClientProxy userClient) {
        this.userClient = userClient;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails result = new UserPrincipal(userClient.findByUsername(username));
        return result;
    }
}
