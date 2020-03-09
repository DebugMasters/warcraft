package com.fenghuolun.modules.utils.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UnifiedOrderRequest {

	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String body;
	private String detail;
	private String attach;
	private String out_trade_no;
	private String fee_type;
	private int total_fee;
	private String spbill_create_ip;
	private String time_start;
	private String time_expire;
	private String goods_tag;
	private String notify_url;
	private String trade_type;
	private String product_id;
	private String openid;

	public UnifiedOrderRequest(String appid, String mch_id, String nonce_str, String out_trade_no, String notify_url, String trade_type) {
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.out_trade_no = out_trade_no;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
	}
	
	public UnifiedOrderRequest() {
		
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getTime_start() {
		return time_start;
	}

	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}

	public String getTime_expire() {
		return time_expire;
	}

	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}

	public String getGoods_tag() {
		return goods_tag;
	}

	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getAttrString() {
		StringBuilder builder = new StringBuilder();

		if (getAppid() != null) {
			builder.append("appid=" + getAppid());
		} else {
			return null;
		}
		if (getAttach() != null) {
			builder.append("&attach=" + getAttach());
		}
		if (getBody() != null) {
			builder.append("&body=" + getBody());
		} else {
			return null;
		}
		if (getDetail() != null) {
			builder.append("&detail=" + getDetail());
		}
		if (getDevice_info() != null) {
			builder.append("&device_info=" + getDevice_info());
		}
		if (getFee_type() != null) {
			builder.append("&fee_type=" + getFee_type());
		}
		if (getGoods_tag() != null) {
			builder.append("&goods_tag=" + getGoods_tag());
		}
		if (getMch_id() != null) {
			builder.append("&mch_id=" + getMch_id());
		} else {
			return null;
		}
		if (getNonce_str() != null) {
			builder.append("&nonce_str=" + getNonce_str());
		} else {
			return null;
		}
		if (getNotify_url() != null) {
			builder.append("&notify_url=" + getNotify_url());
		} else {
			return null;
		}
		if (getOpenid() != null) {
			builder.append("&openid=" + getOpenid());
		}
		if (getOut_trade_no() != null) {
			builder.append("&out_trade_no=" + getOut_trade_no());
		} else {
			return null;
		}
		if (getProduct_id() != null) {
			builder.append("&product_id=" + getProduct_id());
		}
		if (getSpbill_create_ip() != null) {
			builder.append("&spbill_create_ip=" + getSpbill_create_ip());
		} else {
			return null;
		}
		if (getTime_expire() != null) {
			builder.append("&time_expire=" + getTime_expire());
		}
		if (getTime_start() != null) {
			builder.append("&time_start=" + getTime_start());
		}
		if (getTotal_fee() != 0) {
			builder.append("&total_fee=" + getTotal_fee());
		} else {
			return null;
		}
		if (getTrade_type() != null) {
			builder.append("&trade_type=" + getTrade_type());
		} else {
			return null;
		}
		return builder.toString();
	}
}