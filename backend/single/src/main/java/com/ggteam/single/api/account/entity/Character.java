package com.ggteam.single.api.account.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "\"character\"")
public class Character {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "char(1) default 'M'")
    private char gender;

    private int exp;

    private int level;

    private int savepoint;

    private int point;

    private int storyProgress;

    private String imgPath;

    @OneToOne(mappedBy = "character")
    @JsonBackReference
    private Account account;

}
