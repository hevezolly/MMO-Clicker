package com.clicker.Clicker.service.realisations;

import com.clicker.Clicker.entities.Role;
import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.entities.items.Item;
import com.clicker.Clicker.entities.items.MultipleItems;
import com.clicker.Clicker.repos.TeamRepository;
import com.clicker.Clicker.repos.UserRepository;
import com.clicker.Clicker.service.interfaces.ItemManagment;
import com.clicker.Clicker.service.interfaces.UserRequestResult;
import com.clicker.Clicker.service.interfaces.UserManagment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserManagmentImpl implements UserManagment, UserDetailsService {

    private TeamRepository teamRep;
    private UserRepository userRep;
    private ItemManagment itemManagment;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserManagmentImpl(TeamRepository teamRep, UserRepository userRep, ItemManagment itemManagment) {
        this.teamRep = teamRep;
        this.userRep = userRep;
        this.itemManagment = itemManagment;
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

    private long applyItems(long initialValue, MultipleItems[] items, User user){
        var initial = initialValue;
        var userItems = items.clone();
        Arrays.sort(userItems, (i1, i2) -> Integer.compare(i1.getItem().getPriority(), i2.getItem().getPriority()));
        for (var item : userItems) {
            initial = item.getItem().modiphyClicks(initial, user, item.getItemNumber());
        }
        return initial;
    }

    @Override
    public UserRequestResult userClick(String user_name) {
        if (!userRep.existsById(user_name))
            return UserRequestResult.NotExists;
        var user = userRep.getById(user_name);
        var items = new MultipleItems[user.getItems().size()];
        user.getItems().toArray(items);
        var clickCount = applyItems(1L, items, user);
        user.setClickCount(user.getClickCount()+clickCount);
        userRep.save(user);
        var team = user.getCurrent_team();
        if (team != null){
            var teamItems = new MultipleItems[team.getItems().size()];
            team.getItems().toArray(items);
            clickCount = applyItems(clickCount, items, user);
            team.setClick_count(team.getClick_count()+clickCount);
            teamRep.save(team);
        }
        return UserRequestResult.Success;
    }

    @Override
    public UserRequestResult buyItem(String user_name, Item item) {
        var user = getUser(user_name);
        if (user == null)
            return UserRequestResult.NotExists;
        if (user.getClickCount() < item.getCost())
            return UserRequestResult.NotEnoughMoney;

        itemManagment.AddItemTo(item, user);
        user.setClickCount(user.getClickCount() - item.getCost());

        userRep.save(user);

        return UserRequestResult.Success;
    }

    @Override
    public User getUser(String name) {
        return userRep.findById(name).orElse(null);
    }

    @Override
    public User getAuthUser() {
        var userDetail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(userDetail instanceof UserDetails))
            return null;
        var username = ((UserDetails)userDetail).getUsername();
        var user = getUser(username);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var u = getUser(s);
        if (u != null)
            return u;
        throw new UsernameNotFoundException("User with name " + s + " is not found");
    }
}
