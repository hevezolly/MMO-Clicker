package com.clicker.Clicker.service.realisations;

import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.entities.items.*;
import com.clicker.Clicker.repos.ItemRepository;
import com.clicker.Clicker.repos.ItemsTeamsRepository;
import com.clicker.Clicker.repos.ItemsUsersRepository;
import com.clicker.Clicker.service.interfaces.ItemManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemManagmentImpl implements ItemManagment {

    @Autowired
    private ItemRepository itemRep;
    @Autowired
    private ItemsTeamsRepository itemsTeamsRep;
    @Autowired
    private ItemsUsersRepository itemsUsersRep;

    @Override
    public List<Item> GetTeamItems() {
        return itemRep.findByForTeams(true);
    }

    @Override
    public List<Item> GetUserItems() {
        return itemRep.findByForTeams(false);
    }

    @Override
    public void AddItemTo(Item item, User user) {
        var key = new ItemUserKey(item.getId(), user.getUsername());
        var itemUser = new items_users(key);
        if (itemsUsersRep.existsById(key)){
            itemUser = itemsUsersRep.getById(key);
        }
        itemUser.setItem(item);
        itemUser.setItemNumber(itemUser.getItemNumber() + 1);
        itemUser.setOwner(user);
        itemsUsersRep.save(itemUser);
    }

    @Override
    public void AddItemTo(Item item, Team team) {
        var key = new ItemTeamKey(item.getId(), team.getTeam_name());
        var itemTeam = new items_teams(key);
        if (itemsTeamsRep.existsById(key)){
            itemTeam = itemsTeamsRep.getById(key);
        }
        itemTeam.setItem(item);
        itemTeam.setItemNumber(itemTeam.getItemNumber() + 1);
        itemTeam.setOwner(team);
        itemsTeamsRep.save(itemTeam);
    }

    public Item GetIndexedItem(int index){
        if (itemRep.existsById(index))
            return itemRep.getById(index);
        return null;
    }
}
