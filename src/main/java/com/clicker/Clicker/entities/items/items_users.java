package com.clicker.Clicker.entities.items;

import com.clicker.Clicker.entities.User;

import javax.persistence.*;

@Entity
public class items_users extends MultipleItems {

    @EmbeddedId
    private ItemUserKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("username")
    @JoinColumn(name = "username")
    protected User owner;

    public items_users() {
    }

    public items_users(ItemUserKey key) {
        this.id = key;
        this.itemNumber = 0;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
