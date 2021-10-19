package com.clicker.Clicker.controllers;

import com.clicker.Clicker.db.User;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClickProcessor {

    private final UserManagment users;
    private final TeamManagment teams;

    @Autowired
    public ClickProcessor(UserManagment users, TeamManagment teams) {
        this.users = users;
        this.teams = teams;
    }

    @GetMapping("/click/{id}")
    public String get_click(@PathVariable String id){
        var id_int = Integer.parseInt(id);
        users.userClick(id_int);
        var user = users.getUser(id_int);
        var team = user.getCurrent_team();
        return String.format("user clicks: %d, team clicks: %d", user.getClickCount(), team.getClick_count());
    }
}
