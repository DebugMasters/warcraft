/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.order.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;

/**
 * 微信订单记录Entity
 * @author zhengxiaotai
 * @version 2020-05-11
 */
@Table(name="nuanxin_wechat_order", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_id", attrName="userId", label="user_id"),
		@Column(name="out_trade_no", attrName="outTradeNo", label="out_trade_no"),
		@Column(name="total_fee", attrName="totalFee", label="total_fee"),
		@Column(name="order_id", attrName="orderId", label="order_id"),
		@Column(name="open_id", attrName="openId", label="open_id"),
		@Column(name="return_code", attrName="returnCode", label="return_code"),
		@Column(name="return_msg", attrName="returnMsg", label="return_msg"),
		@Column(name="result_code", attrName="resultCode", label="result_code"),
		@Column(name="err_code", attrName="errCode", label="err_code"),
		@Column(name="err_code_des", attrName="errCodeDes", label="err_code_des"),
		@Column(name="data", attrName="data", label="data"),
	}, orderBy="a.id DESC"
)
public class NuanxinWechatOrder extends DataEntity<NuanxinWechatOrder> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// user_id
	private String outTradeNo;		// out_trade_no
	private Integer totalFee;		// total_fee
	private String orderId;		// order_id
	private String openId;		// open_id
	private String returnCode;		// return_code
	private String returnMsg;		// return_msg
	private String resultCode;		// result_code
	private String errCode;		// err_code
	private String errCodeDes;		// err_code_des
	private String data;		// data
	
	public NuanxinWechatOrder() {
		this(null);
	}

	public NuanxinWechatOrder(String id){
		super(id);
	}
	
	@Length(min=0, max=40, message="user_id长度不能超过 40 个字符")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=60, message="out_trade_no长度不能超过 60 个字符")
	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	
	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}
	
	@Length(min=0, max=40, message="order_id长度不能超过 40 个字符")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=40, message="open_id长度不能超过 40 个字符")
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@Length(min=0, max=100, message="return_code长度不能超过 100 个字符")
	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	@Length(min=0, max=200, message="return_msg长度不能超过 200 个字符")
	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}
	
	@Length(min=0, max=100, message="result_code长度不能超过 100 个字符")
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	@Length(min=0, max=100, message="err_code长度不能超过 100 个字符")
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	
	@Length(min=0, max=100, message="err_code_des长度不能超过 100 个字符")
	public String getErrCodeDes() {
		return errCodeDes;
	}

	public void setErrCodeDes(String errCodeDes) {
		this.errCodeDes = errCodeDes;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}