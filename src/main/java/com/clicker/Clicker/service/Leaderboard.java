package com.clicker.Clicker.service;

import com.clicker.Clicker.entities.forms.LeaderboardRow;
import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.repos.TeamRepository;
import com.clicker.Clicker.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class Leaderboard {
    private TeamRepository teamRep;
    private UserRepository userRep;


    @Autowired
    public Leaderboard(TeamRepository teamRep, UserRepository userRep) {
        this.teamRep = teamRep;
        this.userRep = userRep;
    }

    public LeaderboardRow[] getUsersLeaderboardInTeam(Team team) {
        var users = team.getUsers();
        var leaderbord = new LeaderboardRow [users.size()];
        for (int i = 0; i < users.size(); i++) {
            var user = users.get(i);
            leaderbord[i] = new LeaderboardRow(user.getUsername(), user.getClickCount());
        }
        Arrays.sort(leaderbord);
        return leaderbord;
    }

    public LeaderboardRow[] getTeamsLeaderboard() {
        var teams = teamRep.findAll();
        var leaderbord = new LeaderboardRow [teams.size()];
        for (int i = 0; i < teams.size(); i++) {
            var team = teams.get(i);
            leaderbord[i] = new LeaderboardRow(team.getTeam_name(), team.getClick_count());
        }
        Arrays.sort(leaderbord);
        return leaderbord;
    }
}
