package com.ggteam.single.api.account.entity;

import com.ggteam.single.api.account.Role;
import lombok.*;
import org.checkerframework.common.aliasing.qual.Unique;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "\"account\"")
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private String language;

    @Column
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JoinColumn(name = "character_id", unique = true)
    private Character character;

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String newRefreshToken) {
        this.refreshToken = newRefreshToken;
    }

    public void updateNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void updateLanguage(String newLanguage) {
        this.language = newLanguage;
    }

    public void updatePassword(String newPassword, PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(newPassword);
    }
}
