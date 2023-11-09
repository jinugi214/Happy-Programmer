package com.ggteam.single.api.guide.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.guide.entity.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Integer> {

	// 몬스터 이름 검색
	List<Monster> findByNameContaining(String keyword);

	@Query("SELECT m FROM MonsterFavorite mf JOIN Monster m ON mf.id = m.id WHERE mf.account.id = :accountId")
	List<Monster> findMonsterFavoriteByAccount(@Param("accountId") Long accountId);

	Optional<Monster> findById(Integer monsterId);
}
