package com.ggteam.single.api.guide.entity;

import com.ggteam.single.api.account.entity.Account;
import com.ggteam.single.api.account.entity.Character;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Inventory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int count;
    @Column(nullable = false)
    private boolean isEquipping;

    @ManyToOne
    @JoinColumn(name = "character_id")
    private Character character;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public void changeCount(int num) {
        this.count += num;
    }

    @Builder
    public Inventory(int count, boolean isEquipping, Character character, Item item){
        this.count = count;
        this.isEquipping = isEquipping;
        this.item = item;
        this.character = character;
    }
}
