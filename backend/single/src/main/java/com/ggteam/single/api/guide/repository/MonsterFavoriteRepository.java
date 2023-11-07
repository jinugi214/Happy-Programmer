package com.ggteam.single.api.guide.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.guide.entity.Monster;
import com.ggteam.single.api.guide.entity.MonsterFavorite;

public interface MonsterFavoriteRepository extends JpaRepository<MonsterFavorite, Integer> {

	Optional<MonsterFavorite> findByAccountAndMonster(Account account, Monster monster);
}
