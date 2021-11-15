package com.clicker.Clicker.entities.items;

import javax.persistence.*;

@Entity
@Table(name="Items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Item {

    public Item() {
    }

    @Id
    private int id;

    protected String name;

    protected String description;

    protected boolean forTeams;

    public String getName() {
        return name;
    }

    public boolean isForTeams() {
        return forTeams;
    }

    public void setForTeams(boolean forTeams) {
        this.forTeams = forTeams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract long modiphyClicks(long clickCount);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}