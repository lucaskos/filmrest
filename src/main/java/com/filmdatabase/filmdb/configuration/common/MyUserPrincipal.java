package com.filmdatabase.filmdb.configuration.common;

import com.filmdatabase.filmdb.application.model.user.dao.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {

    private User user;

    public MyUserPrincipal(User user) {
        this.user = user;
    }

    //todo add more priviliges
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add((GrantedAuthority) () -> user.getRoles().stream().toString());
        return authorities;
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "MyUserPrincipal{" +
                "user=" + user +
                '}';
    }
}
