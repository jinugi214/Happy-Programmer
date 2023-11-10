package com.ggteam.single.api.account.repository;

import com.ggteam.single.api.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);
    Optional<Account> findByNickname(String nickname);
    Optional<Account> findByRefreshToken(String refreshToken);
    void deleteByUsername(String ussername);

}
