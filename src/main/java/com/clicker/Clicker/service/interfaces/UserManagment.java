package com.clicker.Clicker.service.interfaces;

import com.clicker.Clicker.entities.User;
import com.clicker.Clicker.entities.items.Item;

public interface UserManagment {
    UserRequestResult createUser(String name, String password);
    UserRequestResult userClick(String user_name);
    UserRequestResult buyItem(String user_name, Item item);
    User getUser(String name);
    User getAuthUser();
}
