package com.clicker.Clicker.repos;

import com.clicker.Clicker.entities.items.ItemTeamKey;
import com.clicker.Clicker.entities.items.items_teams;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemsTeamsRepository extends JpaRepository<items_teams, ItemTeamKey> {
    public List<items_teams> findByIdTeamName(String teamName);
}
