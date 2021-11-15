package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.User;

import javax.persistence.*;

@Entity
public class items_users {

    @EmbeddedId
    private ItemUserKey key;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("itemId")
    @JoinColumn(name="id")
    protected Item item;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("username")
    @JoinColumn(name = "username")
    protected User owner;

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

    public items_users() {
    }



    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
