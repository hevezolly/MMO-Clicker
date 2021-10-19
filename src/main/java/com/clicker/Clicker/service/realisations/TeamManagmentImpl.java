package com.clicker.Clicker.service.realisations;

import com.clicker.Clicker.db.Team;
import com.clicker.Clicker.repos.TeamRepository;
import com.clicker.Clicker.repos.UserRepository;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamManagmentImpl implements TeamManagment {
    private final TeamRepository teamRep;
    private final UserRepository userRep;

    @Autowired
    public TeamManagmentImpl(TeamRepository teamRep, UserRepository userRep) {
        this.teamRep = teamRep;
        this.userRep = userRep;
    }

    @Override
    public void createTeam(int admin_id) {
        var admin = userRep.getById(admin_id);
        var team = new Team(admin);
        admin.setCurrent_team(team);
        userRep.save(admin);
        teamRep.save(team);
    }

    @Override
    public void addUser(int team_id, int user_id) {
        if (!teamRep.existsById(team_id) || !userRep.existsById(user_id))
            return;
        var team = teamRep.getById(team_id);
        var user = userRep.getById(user_id);
        user.setCurrent_team(team);
        userRep.save(user);
    }

    @Override
    public void removeFromTeam(int user_id) {
        if (!userRep.existsById(user_id))
            return;
        var user = userRep.getById(user_id);
        user.setCurrent_team(null);
        userRep.save(user);
    }

    @Override
    public Team getTeam(int id) {
        return teamRep.findById(id).orElse(null);
    }
}
