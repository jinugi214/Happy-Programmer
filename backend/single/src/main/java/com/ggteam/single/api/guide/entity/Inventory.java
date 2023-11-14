package com.ggteam.single.api.guide.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ggteam.single.api.account.entity.Character;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
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

    public void changeCount(int count) {
        this.count = count;
    }
    public void addCount(int num) {
        this.count += num;
    }

    @Builder
    public Inventory(int count, boolean isEquipping, Character character, Item item) {
        this.count = count;
        this.isEquipping = isEquipping;
        this.item = item;
        this.character = character;
    }

    public void equip() {
        this.isEquipping = true;
    }
}
