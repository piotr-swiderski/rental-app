package com.swiderski.carrental.security.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDetail extends User implements UserDetails {

    public AuthUserDetail(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authority = new ArrayList<>();
        getRoles().forEach(r -> {
            authority.add(new SimpleGrantedAuthority(r.getName()));
            r.getPermissions().forEach(p -> authority.add(new SimpleGrantedAuthority(p.getName())));
        });
        return authority;
    }
}
