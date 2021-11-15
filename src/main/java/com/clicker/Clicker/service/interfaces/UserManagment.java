package com.clicker.Clicker.service.interfaces;

import com.clicker.Clicker.entities.User;

public interface UserManagment {
    UserRequestResult createUser(String name, String password);
    UserRequestResult userClick(String user_name);
    User getUser(String name);
}
