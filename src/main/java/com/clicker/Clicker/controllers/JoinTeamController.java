package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.forms.UserForm;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinTeamController {

    @Autowired
    private TeamManagment teamManagment;
    @Autowired
    private UserManagment userManagment;

    @GetMapping("/join_team")
    public String getJoin_team(Model model) {
        var user = userManagment.getAuthUser();
        if (user.getCurrent_team() != null)
            return "redirect:/team";
        return "join_team";
    }

    @PostMapping("/join_team")
    private String post(@RequestParam("team_name") String teamName, Model model) {
        var user = userManagment.getAuthUser();
        if (user.getCurrent_team() != null)
            return "redirect:/team";
        var team = teamManagment.getTeam(teamName);
        if (team == null)
            return "redirect:/team?error=true";
        teamManagment.addUser(teamName, user.getUsername());
        return "redirect:/team";
    }
}
