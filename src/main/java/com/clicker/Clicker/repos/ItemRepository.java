package com.clicker.Clicker.repos;

import com.clicker.Clicker.entities.Team;
import com.clicker.Clicker.entities.items.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
