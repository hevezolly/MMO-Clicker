package com.clicker.Clicker.entities.items;

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
    }

    public MultiplayItem(double multiplier, String name, String desc) {
        this.multiplier = multiplier;
        this.name = name;
        this.description = desc;
    }

    @Override
    public long modiphyClicks(long clickCount) {
        return Math.round(clickCount * multiplier);
    }
}
