package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.Team;

import javax.persistence.*;

@Entity
public class items_teams extends MultipleItems {

    @EmbeddedId
    private ItemTeamKey key;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("teamName")
    @JoinColumn(name = "team_name")
    protected Team owner;

    public items_teams() {
    }

    public Team getOwner() {
        return owner;
    }

    public void setOwner(Team owner) {
        this.owner = owner;
    }
}
