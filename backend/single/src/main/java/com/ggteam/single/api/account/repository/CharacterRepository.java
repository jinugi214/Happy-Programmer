package com.ggteam.single.api.account.repository;

import com.ggteam.single.api.account.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    List<Character> findAllByAccountIdByAccountId(String accountId);
}
