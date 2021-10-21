package com.clicker.Clicker.repos;

import com.clicker.Clicker.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
