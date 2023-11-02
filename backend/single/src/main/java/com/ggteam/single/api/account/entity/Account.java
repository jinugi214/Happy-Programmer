package com.ggteam.single.api.account.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "\"account\"")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String accountId;
	private String password;
	private String nickname;
	private String language;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "character_id", referencedColumnName = "id")
	@JsonManagedReference
	private Character character;

}