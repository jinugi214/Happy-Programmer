package com.ggteam.single.api.guide.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggteam.single.api.guide.entity.ItemFavorite;

public interface ItemFavoriteRepository extends JpaRepository<ItemFavorite, Integer> {
}
