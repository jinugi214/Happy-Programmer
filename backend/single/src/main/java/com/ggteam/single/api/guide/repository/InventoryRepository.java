package com.ggteam.single.api.guide.repository;

import java.util.List;

import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findByCharacter(Character character);

	void deleteByCharacter(Character character);
}
