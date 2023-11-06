package com.ggteam.single.api.guide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.entity.Monster;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByNameContaining(String keyword);

	@Query("SELECT i FROM ItemFavorite if JOIN Item i ON if.item.id = i.id WHERE if.account.id = :accountId")
	List<Item> findItemFavoriteByAccount(@Param("accountId") Long accountId);

	Optional<Item> findById(Integer itemId);
}
