package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MultiplayItem")
public class MultiplayItem extends Item {

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        this.multiplier = multiplier;
    }

    private double multiplier;

    public MultiplayItem() {
        this.priority = 10;
    }

    public MultiplayItem(double multiplier, String name, String desc, boolean isForTeam, long cost) {
        this.multiplier = multiplier;
        this.name = name;
        this.description = desc;
        this.forTeams = isForTeam;
        this.cost = cost;
    }

    @Override
    public long modiphyClicks(long clickCount, User user, int numberOfUses) {
        return Math.round(clickCount * (multiplier * numberOfUses + 1));
    }
}
