package com.clicker.Clicker.entities.forms;

import com.clicker.Clicker.entities.items.Item;
import org.springframework.context.annotation.EnableMBeanExport;

public class ItemForm {
    private int index;
    private String name;
    private String description;
    private Long cost;

    public ItemForm(Item item) {
        this.index = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.cost = item.getCost();
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
