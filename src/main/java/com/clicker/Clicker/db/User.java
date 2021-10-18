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
        clickCount = 0;
        current_team = null;
    }

    public int getId() {
        return id;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void incrementClicks(){
        clickCount++;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Team getCurrent_team() {
        return current_team;
    }

    public void setCurrent_team(Team current_team) {
        this.current_team = current_team;
    }
}
