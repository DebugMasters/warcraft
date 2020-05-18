/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * nuanxin_inviteEntity
 * @author zhengxiaotai
 * @version 2020-05-18
 */
@Table(name="nuanxin_invite", alias="a", columns={
		@Column(name="invite_id", attrName="inviteId", label="invite_id", isPK=true),
		@Column(name="invite_user_id", attrName="inviteUserId", label="invite_user_id"),
		@Column(name="invited_user_id", attrName="invitedUserId", label="invited_user_id"),
		@Column(name="invite_time", attrName="inviteTime", label="invite_time"),
	}, orderBy="a.invite_id DESC"
)
public class NuanxinInvite extends DataEntity<NuanxinInvite> {
	
	private static final long serialVersionUID = 1L;
	private String inviteId;		// invite_id
	private String inviteUserId;		// invite_user_id
	private String invitedUserId;		// invited_user_id
	private Date inviteTime;		// invite_time
	
	public NuanxinInvite() {
		this(null);
	}

	public NuanxinInvite(String id){
		super(id);
	}
	
	public String getInviteId() {
		return inviteId;
	}

	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	
	@Length(min=0, max=40, message="invite_user_id长度不能超过 40 个字符")
	public String getInviteUserId() {
		return inviteUserId;
	}

	public void setInviteUserId(String inviteUserId) {
		this.inviteUserId = inviteUserId;
	}
	
	@Length(min=0, max=40, message="invited_user_id长度不能超过 40 个字符")
	public String getInvitedUserId() {
		return invitedUserId;
	}

	public void setInvitedUserId(String invitedUserId) {
		this.invitedUserId = invitedUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInviteTime() {
		return inviteTime;
	}

	public void setInviteTime(Date inviteTime) {
		this.inviteTime = inviteTime;
	}
	
}