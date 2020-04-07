/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_userEntity
 * @author zhengxiaotai
 * @version 2020-03-09
 */
@Table(name="nuanxin_user", alias="a", columns={
		@Column(name="user_id", attrName="userId", label="user_id", isPK=true),
		@Column(name="user_name", attrName="userName", label="user_name", queryType=QueryType.LIKE),
		@Column(name="user_status", attrName="userStatus", label="0=禁用，1=可用"),
		@Column(name="user_type", attrName="userType", label="1=玩家，2=大神"),
		@Column(name="image", attrName="image", label="image", isQuery=false),
		@Column(name="mobile", attrName="mobile", label="mobile", isQuery=false),
		@Column(name="regist_time", attrName="registTime", label="regist_time", isQuery=false),
		@Column(name="wx_no", attrName="wxNo", label="wx_no", isQuery=false),
		@Column(name="wx_openid", attrName="wxOpenid", label="wx_openid", isQuery=false),
		@Column(name="wx_session_key", attrName="wxSessionKey", label="wx_session_key", isQuery=false),
		@Column(name="game_account", attrName="gameAccount", label="game_account", isQuery=false),
		@Column(name="game_password", attrName="gamePassword", label="game_password", isQuery=false),
	}, orderBy="a.user_id DESC"
)
public class NuanxinUser extends DataEntity<NuanxinUser> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// user_id
	private String userName;		// user_name
	private Integer userStatus;		// 0=禁用，1=可用
	private Integer userType;		// 1=玩家，2=大神
	private String image;		// image
	private String mobile;		// mobile
	private Date registTime;		// regist_time
	private String wxNo;		// wx_no
	private String wxOpenid;		// wx_openid
	private String wxSessionKey;		// wx_session_key
	private String gameAccount;
	private String gamePassword;
	
	public NuanxinUser() {
		this(null);
	}

	public NuanxinUser(String id){
		super(id);
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotBlank(message="user_name不能为空")
	@Length(min=0, max=30, message="user_name长度不能超过 30 个字符")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@NotNull(message="0=禁用，1=可用不能为空")
	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	
	@NotNull(message="1=玩家，2=大神不能为空")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@NotBlank(message="image不能为空")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	@Length(min=0, max=20, message="mobile长度不能超过 20 个字符")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="regist_time不能为空")
	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
	
	@Length(min=0, max=50, message="wx_no长度不能超过 50 个字符")
	public String getWxNo() {
		return wxNo;
	}

	public void setWxNo(String wxNo) {
		this.wxNo = wxNo;
	}
	
	@NotBlank(message="wx_openid不能为空")
	@Length(min=0, max=100, message="wx_openid长度不能超过 100 个字符")
	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	
	@NotBlank(message="wx_session_key不能为空")
	@Length(min=0, max=100, message="wx_session_key长度不能超过 100 个字符")
	public String getWxSessionKey() {
		return wxSessionKey;
	}

	public void setWxSessionKey(String wxSessionKey) {
		this.wxSessionKey = wxSessionKey;
	}
	
	public String getGameAccount() {
		return gameAccount;
	}
	
	public void setGameAccount(String gameAccount) {
		this.gameAccount = gameAccount;
	}
	
	public String getGamePassword() {
		return gamePassword;
	}
	
	public void setGamePassword(String gamePassword) {
		this.gamePassword = gamePassword;
	}
}