/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_accountEntity
 * @author zhengxiaotai
 * @version 2020-03-24
 */
@Table(name="nuanxin_account", alias="a", columns={
		@Column(name="account_id", attrName="accountId", label="account_id", isPK=true),
		@Column(name="account_name", attrName="accountName", label="account_name", queryType=QueryType.LIKE),
		@Column(name="user_id", attrName="userId", label="user_id"),
	}, orderBy="a.account_id DESC"
)
public class NuanxinAccount extends DataEntity<NuanxinAccount> {
	
	private static final long serialVersionUID = 1L;
	private String accountId;		// account_id
	private String accountName;		// account_name
	private String userId;		// user_id
	
	public NuanxinAccount() {
		this(null);
	}

	public NuanxinAccount(String id){
		super(id);
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@NotBlank(message="account_name不能为空")
	@Length(min=0, max=30, message="account_name长度不能超过 30 个字符")
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	@NotBlank(message="user_id不能为空")
	@Length(min=0, max=255, message="user_id长度不能超过 255 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}