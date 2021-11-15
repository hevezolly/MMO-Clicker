package com.clicker.Clicker.entities.items;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemUserKey implements Serializable {
    private int itemKey;
    private String username;

    public ItemUserKey() {
    }

    public int getItemKey() {
        return itemKey;
    }

    public void setItemKey(int itemKey) {
        this.itemKey = itemKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemUserKey that = (ItemUserKey) o;
        return itemKey == that.itemKey && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemKey, username);
    }
}
