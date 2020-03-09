package com.fenghuolun.modules.utils.entity;

public class UnifiedOrderResponse {

	private String appid;
	private String partnerid;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String return_code;
	private String return_msg;
	private String trade_type;
	private String prepay_id;
	private String code_url;
	private String timestamp;
	private String mch_id;
	private String out_trade_no;

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPartnerid() {
		return partnerid;
	}

	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
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

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getPrepay_id() {
		return prepay_id;
	}

	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getPackage() {
		return "Sign=WXPay";
	}

	public String getAttrString() {
		StringBuilder builder = new StringBuilder();
		if (getAppid() != null) {
			builder.append("appid=" + getAppid());
		} else {
			return null;
		}
		if (getNonce_str() != null) {
			builder.append("&noncestr=" + getNonce_str());
		} else {
			return null;
		}
		if (getPackage() != null) {
			builder.append("&package=" + getPackage());
		} else {
			return null;
		}
		if (getPartnerid() != null) {
			builder.append("&partnerid=" + getPartnerid());
		} else {
			return null;
		}
		if (getPrepay_id() != null) {
			builder.append("&prepayid=" + getPrepay_id());
		} else {
			return null;
		}
		if (getTimestamp() != null) {
			builder.append("&timestamp=" + getTimestamp());
		} else {
			return null;
		}
		return builder.toString();
	}
}