package com.clicker.Clicker.controllers;

import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.repos.TeamRepository;
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
    private TeamManagment teamManagment;
    @Autowired
    private UserManagment userManagment;

    private String getPage(User user, Model model, Team team){
        model.addAttribute("team_click_count", team.getClick_count());
        if (user.isLeader()) {
            return "team_leader";
        }
        else {
            model.addAttribute("user_click_count", user.getClickCount());
            return "team_user";
        }
    }

    @GetMapping("/team")
    private String getTeam(Model model) {
        var user = userManagment.getAuthUser();
        if (user == null)
            return "redirect:/";
        var team = user.getCurrent_team();
        if (team == null)
            return "redirect:/";
        return getPage(user, model, team);
    }

    @PostMapping("/team")
    private String post(Model model, @RequestParam("command") String command){
        var user = userManagment.getAuthUser();
        if (user == null)
            return "redirect:/";
        if ("leave".equals(command))
            if (!user.isLeader())
                return leave_team(model);
        var team = user.getCurrent_team();
        if ("delete".equals(command))
            if (user.isLeader()) {
                teamManagment.deleteTeam(team);
                return "redirect:/";
            }
        return getPage(user, model, team);
    }

    private String leave_team(Model movel){
        var user = userManagment.getAuthUser();
        if (!user.isLeader()) {
            teamManagment.removeFromTeam(user.getUsername());
            return "redirect:/";
        }
        movel.addAttribute("leaderError", "нельзя покинуть команду, пока вы лидер");
        return "team_leader";
    }
}
