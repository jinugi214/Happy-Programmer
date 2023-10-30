package com.ggteam.single.api.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.inventory.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
