package com.clicker.Clicker.entities;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_role")
public class Role implements GrantedAuthority {
    @Id
    private int id;
    private String name;

    public Role() {
    }

    public Role(int id) {
        this.id = id;
    }

    public static Role getLeader(){
        var r = new Role(2);
        r.setName("ROLE_LEADER");
        return r;
    }

    public static Role getUser(){
        var r = new Role(1);
        r.setName("ROLE_USER");
        return r;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
