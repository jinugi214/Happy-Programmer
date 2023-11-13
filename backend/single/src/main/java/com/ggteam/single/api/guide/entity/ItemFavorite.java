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
public class ItemFavorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Account account;

	@ManyToOne
	private Item item;


	@Builder
	public ItemFavorite(Account account, Item item){
		this.account = account;
		this.item = item;
	}
}
