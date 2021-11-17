package com.clicker.Clicker.entities.items;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemUserKey implements Serializable {

    private int itemId;
    private String username;

    public ItemUserKey() {
    }

    public ItemUserKey(int itemKey, String username) {
        this.itemId = itemKey;
        this.username = username;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
        return itemId == that.itemId && username.equals(that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, username);
    }
}
