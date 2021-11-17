package com.clicker.Clicker.entities.forms;

import com.clicker.Clicker.entities.items.Item;
import org.springframework.context.annotation.EnableMBeanExport;

public class ItemForm {
    private int index;
    private String name;
    private String description;

    public ItemForm(int index, Item item) {
        this.index = index;
        this.name = item.getName();
        this.description = item.getDescription();
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
