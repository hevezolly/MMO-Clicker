package com.clicker.Clicker.service;

import com.clicker.Clicker.entities.Role;
import com.clicker.Clicker.entities.items.AddItem;
import com.clicker.Clicker.entities.items.MultiplayItem;
import com.clicker.Clicker.repos.ItemRepository;
import com.clicker.Clicker.repos.ItemsTeamsRepository;
import com.clicker.Clicker.repos.ItemsUsersRepository;
import com.clicker.Clicker.repos.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseInit {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ItemsUsersRepository itemsUsersRepository;
    @Autowired
    private ItemsTeamsRepository itemsTeamsRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void Init(){
        AddItems();
        AddRoles();
    }

    private void AddRoles(){
        roleRepository.save(Role.getLeader());
        roleRepository.save(Role.getUser());
    }

    private void AddItems(){
        itemRepository.save(new AddItem(1, "+1", "добавляет 1 клику", false, 10));
        itemRepository.save(new MultiplayItem(0.1, "+10%", "добавляет 10% к клику", true, 50));
    }
}
