package com.clicker.Clicker.service.interfaces;

import com.clicker.Clicker.db.User;

public interface UserManagment {
    void createUser(String name, String password);
    void renameUser(int user_id, String new_name);
    void userClick(int user_id);
    User getUser(int id);
}
