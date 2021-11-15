package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.forms.TeamPageAction;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {
    @Autowired
    private TeamManagment teamServace;
    @Autowired
    private UserManagment userServace;

    private String getPage(User user){
        if (user.isLeader())
            return "team_leader"; //TODO: team_leader.jsp
        else
            return "team_user";
    }

    @GetMapping("/team")
    public String getTeam(Model model) {
        var user = userServace.getAuthUser();
        if (user == null)
            return "redirect:/";
        var team = user.getCurrent_team();
        if (team == null)
            return "redirect:/";
        model.addAttribute("teamAction", new TeamPageAction());
        return getPage(user);
    }

    @PostMapping("/team")
    private String post(@RequestParam("teamAction") TeamPageAction actions, Model model){
        var user = userServace.getAuthUser();
        if (user == null)
            return "redirect:/";
        if (actions.isLeaveTeam())
            return live_team(model);
        return getPage(user);
    }

    private String live_team(Model movel){
        var user = userServace.getAuthUser();
        if (!user.isLeader()) {
            teamServace.removeFromTeam(user.getUsername());
            return "redirect:/";
        }
        movel.addAttribute("leaderError", "нельзя покинуть команду, пока вы лидер");
        return "team_leader";
    }
}
