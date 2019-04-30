package com.yx.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: JST
 * @Date: 2019/4/23 14:35
 */
@Data
public class LoginUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Set<SysRole> roles;

    private Set<String> authority;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                if (role.getRoleName().startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role.getRoleName()));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
                }
            });
        }

        if (!CollectionUtils.isEmpty(authority)) {
            authority.forEach(per -> {
                collection.add(new SimpleGrantedAuthority(per));
            });
        }

        return collection;
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
}
