/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * nuanxin_orderEntity
 * @author zhengxiaotai
 * @version 2020-04-13
 */
@Table(name="nuanxin_order", alias="a", columns={
		@Column(name="order_id", attrName="orderId", label="订单ID", isPK=true),
		@Column(name="user_id", attrName="userId", label="用户ID"),
		@Column(name="order_type", attrName="orderType", label="订单类型", isQuery=false),
		@Column(name="order_server", attrName="orderServer", label="服务器类型"),
		@Column(name="order_catalog_1", attrName="orderCatalog1", label="代练类别1"),
		@Column(name="order_catalog_2", attrName="orderCatalog2", label="代练类别2"),
		@Column(name="order_catalog_3", attrName="orderCatalog3", label="代练类别3"),
		@Column(name="order_money", attrName="orderMoney", label="订单金额", isQuery=false),
		@Column(name="order_status", attrName="orderStatus", label="订单状态"),
		@Column(name="character_id", attrName="characterId", label="角色ID", isQuery=false),
		@Column(name="character_spec", attrName="characterSpec", label="角色专精", isQuery=false),
		@Column(name="create_time", attrName="createTime", label="下单时间", isQuery=false),
		@Column(name="account_id", attrName="accountId", label="账户", isQuery=false),
		@Column(name="account_password", attrName="accountPassword", label="密码", isQuery=false),
		@Column(name="saveguard", attrName="saveguard", label="是否有安全令", isQuery=false),
		@Column(name="phone", attrName="phone", label="手机", isQuery=false),
		@Column(name="note", attrName="note", label="备注", isQuery=false),
	}, orderBy="a.order_id DESC"
)
public class NuanxinOrder extends DataEntity<NuanxinOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单ID
	private String userId;		// 用户ID
	private Integer orderType;		// 订单类型
	private Integer orderServer;		// 服务器类型
	private String orderCatalog1;		// 代练类别1
	private String orderCatalog2;		// 代练类别2
	private String orderCatalog3;		// 代练类别3
	private Double orderMoney;		// 订单金额
	private Integer orderStatus;		// 订单状态
	private String characterId;		// 角色ID
	private String characterSpec;	// 角色专精
	private Date createTime;		// 下单时间
	private String accountId;		// 账户
	private String accountPassword;		// 密码
	private Integer saveguard;		// 是否有安全令
	private String phone;		// 手机
	private String note;		// 备注
	
	private String catalogName1;
	private String catalogName2;
	private String catalogName3;
	
	public NuanxinOrder() {
		this(null);
	}

	public NuanxinOrder(String id){
		super(id);
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@NotBlank(message="用户ID不能为空")
	@Length(min=0, max=40, message="用户ID长度不能超过 40 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@NotNull(message="订单类型不能为空")
	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	@NotNull(message="服务器类型不能为空")
	public Integer getOrderServer() {
		return orderServer;
	}

	public void setOrderServer(Integer orderServer) {
		this.orderServer = orderServer;
	}
	
	@Length(min=0, max=20, message="代练类别1长度不能超过 20 个字符")
	public String getOrderCatalog1() {
		return orderCatalog1;
	}

	public void setOrderCatalog1(String orderCatalog1) {
		this.orderCatalog1 = orderCatalog1;
	}
	
	@Length(min=0, max=20, message="代练类别2长度不能超过 20 个字符")
	public String getOrderCatalog2() {
		return orderCatalog2;
	}

	public void setOrderCatalog2(String orderCatalog2) {
		this.orderCatalog2 = orderCatalog2;
	}
	
	@Length(min=0, max=20, message="代练类别3长度不能超过 20 个字符")
	public String getOrderCatalog3() {
		return orderCatalog3;
	}

	public void setOrderCatalog3(String orderCatalog3) {
		this.orderCatalog3 = orderCatalog3;
	}
	
	@NotNull(message="订单金额不能为空")
	public Double getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	
	@NotNull(message="订单状态不能为空")
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@NotBlank(message="角色ID不能为空")
	@Length(min=0, max=30, message="角色ID长度不能超过 30 个字符")
	public String getCharacterId() {
		return characterId;
	}

	public void setCharacterId(String characterId) {
		this.characterId = characterId;
	}
	
	public String getCharacterSpec() {
		return characterSpec;
	}
	
	public void setCharacterSpec(String characterSpec) {
		this.characterSpec = characterSpec;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=30, message="账户长度不能超过 30 个字符")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	@Length(min=0, max=30, message="密码长度不能超过 30 个字符")
	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	
	public Integer getSaveguard() {
		return saveguard;
	}

	public void setSaveguard(Integer saveguard) {
		this.saveguard = saveguard;
	}
	
	@Length(min=0, max=20, message="手机长度不能超过 20 个字符")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=255, message="备注长度不能超过 255 个字符")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public String getCatalogName1() {
		return catalogName1;
	}
	
	public void setCatalogName1(String catalogName1) {
		this.catalogName1 = catalogName1;
	}
	
	public String getCatalogName2() {
		return catalogName2;
	}
	
	public void setCatalogName2(String catalogName2) {
		this.catalogName2 = catalogName2;
	}
	
	public String getCatalogName3() {
		return catalogName3;
	}
	
	public void setCatalogName3(String catalogName3) {
		this.catalogName3 = catalogName3;
	}
}