package com.clicker.Clicker.service.realisations;

import com.clicker.Clicker.db.User;
import com.clicker.Clicker.repos.TeamRepository;
import com.clicker.Clicker.repos.UserRepository;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagmentImpl implements UserManagment {
    private final TeamRepository teamRep;
    private final UserRepository userRep;

    @Autowired
    public UserManagmentImpl(TeamRepository teamRep, UserRepository userRep) {
        this.teamRep = teamRep;
        this.userRep = userRep;
    }

    @Override
    public void createUser(String name, String password) {
        var user = new User(name, password);
        userRep.save(user);
    }

    @Override
    public void renameUser(int user_id, String new_name) {
        if (!userRep.existsById(user_id))
            return;
        var user = userRep.getById(user_id);
        user.setName(new_name);
        userRep.save(user);
    }

    @Override
    public void userClick(int user_id) {
        if (!userRep.existsById(user_id))
            return;
        var user = userRep.getById(user_id);
        user.incrementClicks();
        userRep.save(user);
        var team = user.getCurrent_team();
        if (team != null){
            team.clickIncrement();
            teamRep.save(team);
        }
    }

    @Override
    public User getUser(int id) {
        if (!userRep.existsById(id))
            return null;
        return userRep.getById(id);
    }
}
