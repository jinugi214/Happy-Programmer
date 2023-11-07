package com.ggteam.single.api.guide.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ggteam.single.api.account.entity.Account;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class MonsterFavorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Account account;

	@ManyToOne
	private Monster monster;

	@Builder
	public MonsterFavorite(Account account, Monster monster){
		this.account = account;
		this.monster = monster;
	}
}
