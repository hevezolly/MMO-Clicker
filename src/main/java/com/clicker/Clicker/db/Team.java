package com.clicker.Clicker.db;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Teams")
public class Team {

    @Id
    private int id;

    @Column(name="click_count")
    private int click_count;

    @OneToOne
    @JoinColumn(name="admin")
    private final User admin;

    @OneToMany(mappedBy="current_team")
    private List<User> users;

    public Team(User admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void clickIncrement(){
        click_count++;
    }

    public int getClick_count() {
        return click_count;
    }

    public User getAdmin() {
        return admin;
    }
}
