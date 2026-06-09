package com.hediyesilaozyurt.entities.authEntities;

import com.hediyesilaozyurt.entities.base.BaseEntity;
import com.hediyesilaozyurt.entities.enums.UserRole;
import com.hediyesilaozyurt.entities.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user",schema = "gallery_management")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

    @Column(name="username",nullable = false,unique = true)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status=UserStatus.PENDING_PROFILE;

    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }
}
