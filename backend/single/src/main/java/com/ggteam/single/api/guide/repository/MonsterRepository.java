package com.ggteam.single.api.guide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ggteam.single.api.guide.entity.Monster;

@Repository
public interface MonsterRepository extends JpaRepository<Monster, Long> {

}
