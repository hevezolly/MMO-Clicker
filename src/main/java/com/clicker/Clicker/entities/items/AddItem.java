package com.clicker.Clicker.entities.items;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AddItem")
public class AddItem extends Item {

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    private long number;

    public AddItem() {
    }

    public AddItem(long number, String name, String desc) {
        this.number = number;
        this.name = name;
        this.description = desc;
    }

    @Override
    public long modiphyClicks(long clickCount) {
        return clickCount + number;
    }
}
