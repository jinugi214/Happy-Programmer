package com.ggteam.single.api.account.entity;

import com.ggteam.single.api.guide.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ColumnDefault("1")
    private int level;

    private String savepoint;

    private int point;

    private int storyProgress;

    private String imgPath;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "character")
    private List<Inventory> inventoryList = new ArrayList<>();

    // 게임 저장시 캐릭터 업데이트
    public void updateCharacter(int exp, int level, String savepoint, int point, int storyProgress, String imgPath){
        this.exp = exp;
        this.level = level;
        this.savepoint = savepoint;
        this.point = point;
        this.storyProgress = storyProgress;
        this.imgPath = imgPath;
    }
}
