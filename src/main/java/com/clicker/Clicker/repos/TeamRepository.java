package com.clicker.Clicker.repos;

import com.clicker.Clicker.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, String> {
}
