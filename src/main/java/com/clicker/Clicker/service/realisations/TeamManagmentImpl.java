package com.clicker.Clicker.service.realisations;

import com.clicker.Clicker.entities.Role;
import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.items.Item;
import com.clicker.Clicker.repos.TeamRepository;
import com.clicker.Clicker.repos.UserRepository;
import com.clicker.Clicker.service.interfaces.ItemManagment;
import com.clicker.Clicker.service.interfaces.TeamRequestResult;
import com.clicker.Clicker.service.interfaces.UserRequestResult;
import com.clicker.Clicker.service.interfaces.TeamManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamManagmentImpl implements TeamManagment{

    private TeamRepository teamRep;
    private UserRepository userRep;

    private ItemManagment itemManagment;


    @Autowired
    public TeamManagmentImpl(TeamRepository teamRep, UserRepository userRep, ItemManagment itemManagment) {
        this.teamRep = teamRep;
        this.userRep = userRep;
        this.itemManagment = itemManagment;
    }

    @Override
    public TeamRequestResult createTeam(String leader_username, String team_name) {
        if (teamRep.existsById(team_name))
            return TeamRequestResult.TeamExists;
        if (!userRep.existsById(leader_username))
            return TeamRequestResult.UserNotExists;
        var leader = userRep.getById(leader_username);
        if (leader.getCurrent_team() != null)
            return TeamRequestResult.UserHasTeam;
        var t = new Team();
        t.setAdmin(leader);
        t.setClick_count(leader.getClickCount());
        t.setTeam_name(team_name);
        teamRep.save(t);
        leader.setCurrent_team(t);
        leader.getRoles().add(Role.getLeader());
        userRep.save(leader);
        return TeamRequestResult.Success;
    }

    @Override
    public TeamRequestResult addUser(String team_name, String user_name) {
        if (!teamRep.existsById(team_name))
            return TeamRequestResult.TeamNotExists;
        if (!userRep.existsById(user_name))
            return TeamRequestResult.UserNotExists;
        var user = userRep.getById(user_name);
        var team = teamRep.getById(team_name);
        if (user.getCurrent_team() != null){
            return TeamRequestResult.UserHasTeam;
        }
        user.setCurrent_team(team);
        userRep.save(user);
        return TeamRequestResult.Success;
    }

    @Override
    public TeamRequestResult removeFromTeam(String user_name) {
        if (!userRep.existsById(user_name))
            return TeamRequestResult.UserNotExists;
        var user = userRep.getById(user_name);
        if (user.getCurrent_team() == null)
            return TeamRequestResult.TeamNotExists;
        if (user.getRoles().contains(Role.getLeader()))
            return TeamRequestResult.UserIsLeader;
        user.setCurrent_team(null);
        userRep.save(user);
        return TeamRequestResult.Success;
    }

    @Override
    public TeamRequestResult deleteTeam(Team team) {
        for (var user:
             team.getUsers()) {
            user.setCurrent_team(null);
            userRep.save(user);
        }
        teamRep.deleteById(team.getTeam_name());
        return TeamRequestResult.Success;
    }

    @Override
    public Team getTeam(String name) {
        return teamRep.findById(name).orElse(null);
    }

    @Override
    public TeamRequestResult BuyItem(Team team, Item item) {
        if (team.getClick_count() < item.getCost())
            return TeamRequestResult.NotEnoughMoney;

        itemManagment.AddItemTo(item, team);
        team.setClick_count(team.getClick_count() - item.getCost());

        teamRep.save(team);

        return TeamRequestResult.Success;
    }
}
