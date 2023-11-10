package com.ggteam.single.api.guide.entity;

import com.ggteam.single.api.account.entity.Character;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    public void equip() {
        this.isEquipping = true;
    }
}
