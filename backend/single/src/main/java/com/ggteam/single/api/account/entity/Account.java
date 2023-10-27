package com.ggteam.single.api.account.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ggteam.single.api.account.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Account {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String accountId;
    private String password;
    private String nickname;
    private String language;
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void authorizeUser() {
        this.role = Role.USER;
    }

    public void passwordEncode(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    @JsonManagedReference
    private Character character;
}
