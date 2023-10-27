package com.ggteam.single.api.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSignUpDto {

    private String accountId;
    private String password;
    private String nickname;
    private String language;
}
