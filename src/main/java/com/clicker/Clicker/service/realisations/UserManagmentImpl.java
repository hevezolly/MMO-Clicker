package com.clicker.Clicker.service.realisations;

import com.clicker.Clicker.entities.Role;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.repos.TeamRepository;
import com.clicker.Clicker.repos.UserRepository;
import com.clicker.Clicker.service.interfaces.UserRequestResult;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserManagmentImpl implements UserManagment, UserDetailsService {

    private TeamRepository teamRep;
    private UserRepository userRep;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserManagmentImpl(TeamRepository teamRep, UserRepository userRep) {
        this.teamRep = teamRep;
        this.userRep = userRep;
    }

    @Override
    public UserRequestResult createUser(String name, String password) {
        if (userRep.existsById(name))
            return UserRequestResult.Exists;
        var u = new User();
        var roles = new HashSet<Role>();
        roles.add(Role.getUser());
        u.setUsername(name);
        u.setPassword(bCryptPasswordEncoder.encode(password));
        u.setRoles(roles);
        userRep.save(u);
        return UserRequestResult.Success;
    }

    @Override
    public UserRequestResult userClick(String user_name) {
        if (!userRep.existsById(user_name))
            return UserRequestResult.NotExists;
        var user = userRep.getById(user_name);
        user.setClickCount(user.getClickCount()+1);
        userRep.save(user);
        var team = user.getCurrent_team();
        if (team != null){
            team.setClick_count(team.getClick_count()+1);
            teamRep.save(team);
        }
        return UserRequestResult.Success;
    }

    @Override
    public User getUser(String name) {
        return userRep.findById(name).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var u = getUser(s);
        if (u != null)
            return u;
        throw new UsernameNotFoundException("User with name " + s + " is not found");
    }
}
