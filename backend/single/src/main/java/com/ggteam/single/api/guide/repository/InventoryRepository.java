package com.ggteam.single.api.guide.repository;

import java.util.List;

import com.ggteam.single.api.account.entity.Character;
import com.ggteam.single.api.guide.entity.Inventory;
import com.ggteam.single.api.guide.entity.Item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByCharacterIdAndItemId(Long characterId, Integer itemId);

    List<Inventory> findAllByCharacterId(Long characterId);

	List<Inventory> findByCharacter(Character character);

	void deleteByCharacter(Character character);

}
