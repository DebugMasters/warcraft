/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * nuanxin_couponEntity
 * @author zhengxiaotai
 * @version 2020-05-05
 */
@Table(name="nuanxin_coupon", alias="a", columns={
		@Column(name="coupon_id", attrName="couponId", label="coupon_id", isPK=true),
		@Column(name="coupon_name", attrName="couponName", label="coupon_name", queryType=QueryType.LIKE),
		@Column(name="coupon_money", attrName="couponMoney", label="coupon_money", isQuery=false),
		@Column(name="coupon_discount", attrName="couponDiscount", label="coupon_discount"),
		@Column(name="coupon_status", attrName="couponStatus", label="coupon_status"),
		@Column(name="user_id", attrName="userId", label="user_id"),
		@Column(name="expire_time", attrName="expireTime", label="expire_time", isQuery=false),
		@Column(name="order_id", attrName="orderId", label="order_id"),
	}, orderBy="a.coupon_id DESC"
)
public class NuanxinCoupon extends DataEntity<NuanxinCoupon> {
	
	private static final long serialVersionUID = 1L;
	private String couponId;		// coupon_id
	private String couponName;		// coupon_name
	private Double couponMoney;		// coupon_money
	private Double couponDiscount;		// coupon_discount
	private Integer couponStatus;		// coupon_status
	private String userId;		// user_id
	private Date expireTime;		// expire_time
	private String orderId;		// order_id
	
	public NuanxinCoupon() {
		this(null);
	}

	public NuanxinCoupon(String id){
		super(id);
	}
	
	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	
	@NotBlank(message="coupon_name不能为空")
	@Length(min=0, max=30, message="coupon_name长度不能超过 30 个字符")
	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	public Double getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Double couponMoney) {
		this.couponMoney = couponMoney;
	}
	
	public Double getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(Double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	
	public Integer getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}
	
	@NotBlank(message="user_id不能为空")
	@Length(min=0, max=40, message="user_id长度不能超过 40 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	
	@Length(min=0, max=30, message="order_id长度不能超过 30 个字符")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}