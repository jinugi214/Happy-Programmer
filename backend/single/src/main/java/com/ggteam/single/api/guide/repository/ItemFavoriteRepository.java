package com.ggteam.single.api.guide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.dto.res.ItemResponse;
import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.entity.ItemFavorite;

public interface ItemFavoriteRepository extends JpaRepository<ItemFavorite, Integer> {

	Optional<ItemFavorite> findByAccountAndItem(Account account, Item item);

}
