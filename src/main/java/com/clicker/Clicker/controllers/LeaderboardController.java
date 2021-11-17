package com.clicker.Clicker.controllers;

import com.clicker.Clicker.service.Leaderboard;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {
    @Autowired
    private Leaderboard leaderboard;
    @Autowired
    private UserManagment userService;

    @GetMapping("/leaderboards")
    private String getLeaderbords(Model model) {
        var teamsLeaderboard = leaderboard.getTeamsLeaderboard();
        var user = userService.getAuthUser();
        if (user != null) {
            var team = user.getCurrent_team();
            if (team != null) {
                var usersLeaderboardInTeam = leaderboard.getUsersLeaderboardInTeam(team);
                model.addAttribute("teamName", team.getTeam_name());
                model.addAttribute("usersLeaderboard", usersLeaderboardInTeam);
            }
        }
        model.addAttribute("teamsLeaderboard", teamsLeaderboard);
        return "leaderboards";
    }
}
