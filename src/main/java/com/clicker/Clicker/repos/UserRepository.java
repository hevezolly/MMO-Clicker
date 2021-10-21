package com.clicker.Clicker.repos;

import com.clicker.Clicker.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
