package com.clicker.Clicker.service.interfaces;

import com.clicker.Clicker.db.Team;
import com.clicker.Clicker.db.User;

public interface TeamManagment {
    void createTeam(int admin_id);
    void addUser(int team_id, int user_id);
    void removeFromTeam(int user_id);
    Team getTeam(int id);
}
