package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.User;

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
        this.priority = 0;
    }

    public AddItem(long number, String name, String desc) {
        this.number = number;
        this.name = name;
        this.description = desc;
        this.priority = 0;
    }

    @Override
    public long modiphyClicks(long clickCount, User user, int numberOfUses)
    {
        return clickCount + number * numberOfUses;
    }
}
