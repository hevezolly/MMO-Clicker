package com.clicker.Clicker.db;

import javax.persistence.*;

@Entity
@Table(name="Users")
public class User {

    @Id
    private int id;

    @Column(name="name")
    private String name;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team current_team;

    @Column(name="click_count")
    private int clickCount;

    @Column(name="password")
    private String password;

    public User(String name, String password){
        this.name = name;
        this.password = password;
    }
}
