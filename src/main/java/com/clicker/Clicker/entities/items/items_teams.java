package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.Team;

import javax.persistence.*;

@Entity
public class items_teams {

    @EmbeddedId
    private ItemTeamKey key;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("itemId")
    @JoinColumn(name="id")
    protected Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("teamName")
    @JoinColumn(name = "team_name")
    protected Team owner;

    protected int itemNumber;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public items_teams() {
    }



    public Team getOwner() {
        return owner;
    }

    public void setOwner(Team owner) {
        this.owner = owner;
    }
}
