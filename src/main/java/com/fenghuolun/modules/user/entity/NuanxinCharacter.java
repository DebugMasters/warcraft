/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_characterEntity
 * @author zhengxiaotai
 * @version 2020-03-26
 */
@Table(name="nuanxin_character", alias="a", columns={
		@Column(name="character_id", attrName="characterId", label="character_id", isPK=true),
		@Column(name="character_name", attrName="characterName", label="character_name", queryType=QueryType.LIKE),
		@Column(name="character_account", attrName="characterAccount", label="character_account"),
		@Column(name="character_realm_type", attrName="characterRealmType", label="character_realm_type"),
		@Column(name="character_class", attrName="characterClass", label="character_class"),
		@Column(name="alliance_horde", attrName="allianceHorde", label="alliance_horde"),
		@Column(name="character_specialization", attrName="characterSpecialization", label="character_specialization"),
		@Column(name="character_realm", attrName="characterRealm", label="character_realm"),
		@Column(name="user_id", attrName="userId", label="user_id"),
	}, orderBy="a.character_id DESC"
)
public class NuanxinCharacter extends DataEntity<NuanxinCharacter> {
	
	private static final long serialVersionUID = 1L;
	private String characterId;		// character_id
	private String characterName;		// character_name
	private String characterAccount;		// character_account
	private Integer characterRealmType;		// character_realm_type
	private Integer characterClass;		// character_class
	private Integer allianceHorde;		// alliance_horde
	private String characterSpecialization;		// character_specialization
	private String characterRealm;		// character_realm
	private String userId;		// user_id
	
	public NuanxinCharacter() {
		this(null);
	}

	public NuanxinCharacter(String id){
		super(id);
	}
	
	public String getCharacterId() {
		return characterId;
	}

	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}
	
	@Length(min=0, max=20, message="character_name长度不能超过 20 个字符")
	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	public String getCharacterAccount() {
		return characterAccount;
	}

	public void setCharacterAccount(String characterAccount) {
		this.characterAccount = characterAccount;
	}
	
	public Integer getCharacterRealmType() {
		return characterRealmType;
	}

	public void setCharacterRealmType(Integer characterRealmType) {
		this.characterRealmType = characterRealmType;
	}
	
	public Integer getCharacterClass() {
		return characterClass;
	}

	public void setCharacterClass(Integer characterClass) {
		this.characterClass = characterClass;
	}
	
	public Integer getAllianceHorde() {
		return allianceHorde;
	}

	public void setAllianceHorde(Integer allianceHorde) {
		this.allianceHorde = allianceHorde;
	}
	
	public String getCharacterSpecialization() {
		return characterSpecialization;
	}

	public void setCharacterSpecialization(String characterSpecialization) {
		this.characterSpecialization = characterSpecialization;
	}
	
	public String getCharacterRealm() {
		return characterRealm;
	}

	public void setCharacterRealm(String characterRealm) {
		this.characterRealm = characterRealm;
	}
	
	@Length(min=0, max=30, message="user_id长度不能超过 30 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}