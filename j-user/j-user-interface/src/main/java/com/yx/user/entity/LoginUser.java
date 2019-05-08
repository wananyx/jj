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

    /**
     * @see org.springframework.security.core.userdetails.User
     * 将权限信息赋值给框架用户实体类，用于鉴权( 在方法上添加@PreAuthorize("hasRole('ROLE_ADMIN')") )
     */
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();

        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                if (role.getCode().startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role.getCode()));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
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
        return getEnabled();
    }
}
