package com.clicker.Clicker.repos;

import com.clicker.Clicker.entities.items.ItemUserKey;
import com.clicker.Clicker.entities.items.items_users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsUsersRepository extends JpaRepository<items_users, ItemUserKey> {
}
