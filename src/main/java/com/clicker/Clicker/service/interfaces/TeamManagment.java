package com.clicker.Clicker.service.interfaces;

import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.items.Item;

public interface TeamManagment {
    TeamRequestResult createTeam(String admin_username, String team_name);
    TeamRequestResult addUser(String team_name, String user_name);
    TeamRequestResult removeFromTeam(String user_name);
    TeamRequestResult deleteTeam(Team team);
    Team getTeam(String name);
    TeamRequestResult BuyItem(Team team, Item item);
}
