/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.system.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_realm_listEntity
 * @author zhengxiaotai
 * @version 2020-03-26
 */
@Table(name="nuanxin_realm_list", alias="a", columns={
		@Column(name="realm_id", attrName="realmId", label="realm_id", isPK=true),
		@Column(name="realm_name", attrName="realmName", label="realm_name", queryType=QueryType.LIKE),
		@Column(name="realm_zone", attrName="realmZone", label="realm_zone"),
		@Column(name="realm_type", attrName="realmType", label="realm_type"),
	}, orderBy="a.realm_id DESC"
)
public class NuanxinRealmList extends DataEntity<NuanxinRealmList> {
	
	private static final long serialVersionUID = 1L;
	private Integer realmId;		// realm_id
	private String realmName;		// realm_name
	private Integer realmZone;		// realm_zone
	private Integer realmType;		// realm_type
	
	public NuanxinRealmList() {
		this(null);
	}

	public NuanxinRealmList(String id){
		super(id);
	}
	
	public Integer getRealmId() {
		return realmId;
	}

	public void setRealmId(Integer realmId) {
		this.realmId = realmId;
	}
	
	@Length(min=0, max=20, message="realm_name长度不能超过 20 个字符")
	public String getRealmName() {
		return realmName;
	}

	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}
	
	public Integer getRealmZone() {
		return realmZone;
	}

	public void setRealmZone(Integer realmZone) {
		this.realmZone = realmZone;
	}
	
	public Integer getRealmType() {
		return realmType;
	}

	public void setRealmType(Integer realmType) {
		this.realmType = realmType;
	}
	
}