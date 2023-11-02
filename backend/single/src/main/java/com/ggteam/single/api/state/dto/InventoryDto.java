package com.ggteam.single.api.state.dto;


import javax.persistence.*;

// @Entity(name = "inventory")
public class InventoryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private CharacterDto characterDto;

    private int count;

    private boolean isEquipping;


    // 추후 FK 수정해야함.

}
