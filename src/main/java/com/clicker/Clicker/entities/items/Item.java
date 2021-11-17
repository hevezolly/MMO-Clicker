package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.User;

import javax.persistence.*;

@Entity
@Table(name="Items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
public abstract class Item {

    public Item() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    protected Long cost;

    protected String name;

    protected String description;

    protected boolean forTeams;

    protected int priority;

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

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

    public abstract long modiphyClicks(long clickCount, User user, int numberOfUses);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }
}