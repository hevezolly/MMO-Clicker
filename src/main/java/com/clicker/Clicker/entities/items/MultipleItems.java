package com.clicker.Clicker.entities.items;

import javax.persistence.*;

@MappedSuperclass
public abstract class MultipleItems {

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("itemId")
    @JoinColumn(name="id")
    protected Item item;

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
}
