package com.clicker.Clicker.entities;

import com.clicker.Clicker.entities.items.Item;
import com.clicker.Clicker.entities.items.items_users;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Users")
public class User implements UserDetails {

    @Id
    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_name")
    private Team current_team;

    @Column(name="click_count")
    private long clickCount = 0;

    @Column(name="password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @OneToMany(mappedBy = "owner")
    private Set<items_users> items_users;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCurrent_team(Team current_team) {
        this.current_team = current_team;
    }

    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Team getCurrent_team() {
        return current_team;
    }

    public long getClickCount() {
        return clickCount;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Boolean isLeader() {return getRoles().contains(Role.getLeader());}
}
