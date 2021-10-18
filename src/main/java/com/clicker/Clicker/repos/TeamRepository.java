package com.clicker.Clicker.repos;

import com.clicker.Clicker.db.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
