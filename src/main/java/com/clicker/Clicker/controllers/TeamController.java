package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.TeamForm;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class TeamController {
    @Autowired
    private TeamManagment teamServace;
    @Autowired
    private UserManagment userServace;

    @GetMapping("/team")
    public String createTeam(Model model) {
        var userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetail instanceof UserDetails))
            return "/";
        var username = ((UserDetails)userDetail).getUsername();
        var team = userServace.getUser(username).getCurrent_team();
        if (team == null)
            return "/";
        var admin = team.getAdmin();
        if (admin.getUsername().equals(username))
            return "team_leader"; //TODO: team_leader.jsp
        else
            return "team_user";
    }
}
