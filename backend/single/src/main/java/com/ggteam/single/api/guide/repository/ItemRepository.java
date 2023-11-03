package com.ggteam.single.api.guide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.guide.entity.Item;
import com.ggteam.single.api.guide.entity.Monster;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByNameContaining(String keyword);
}
