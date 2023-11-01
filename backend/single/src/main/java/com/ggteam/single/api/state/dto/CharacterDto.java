package com.ggteam.single.api.state.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // 유저 dto 생성되면 넣어야 함.


    private String name;

    private String gender;


    private int exp;

    private int level;

    private int savepoint;




}
