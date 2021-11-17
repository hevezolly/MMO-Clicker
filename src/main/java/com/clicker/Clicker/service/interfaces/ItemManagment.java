package com.clicker.Clicker.service.interfaces;

import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.entities.items.Item;
import com.clicker.Clicker.entities.items.MultipleItems;

import java.util.List;

public interface ItemManagment {
    List<Item> GetTeamItems();
    List<Item> GetUserItems();
    void AddItemTo(Item item, User user);
    void AddItemTo(Item item, Team team);
    Item GetIndexedItem(int index);
}
