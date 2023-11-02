package com.ggteam.single.api.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {

    private Long Id;
    private String name;
    private char gender;
    private int exp;
    private int level;
    private int savepoint;
    private String imgPath;

}
