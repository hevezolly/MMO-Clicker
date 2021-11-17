package com.clicker.Clicker.entities.items;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ItemTeamKey implements Serializable {
    private int itemId;
    private String teamName;

    public ItemTeamKey() {
    }

    public ItemTeamKey(int itemId, String teamName) {
        this.itemId = itemId;
        this.teamName = teamName;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTeamKey that = (ItemTeamKey) o;
        return itemId == that.itemId && teamName.equals(that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, teamName);
    }
}
