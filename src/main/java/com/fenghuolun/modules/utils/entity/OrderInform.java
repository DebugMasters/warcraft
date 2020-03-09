package com.fenghuolun.modules.utils.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.w3c.dom.Document;

@XmlRootElement
public class OrderInform {

	private String return_code;
	private String return_msg;
	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String openid;
	private String is_subscribe;
	private String trade_type;
	private String bank_type;
	private int total_fee;
	private String fee_type;
	private int cash_fee;
	private String cash_fee_type;
	private int coupon_fee;
	private int coupon_count;
	private String coupon_batch_id_$n;
	private String coupon_id_$n;
	private int coupon_fee_$n;
	private String transaction_id;
	private String out_trade_no;
	private String attach;
	private String time_end;

	public void read(Document document) {
		if (document.getElementsByTagName("return_code") != null && document.getElementsByTagName("return_code").getLength() > 0)
			return_code = document.getElementsByTagName("return_code").item(0).getTextContent();

		if (document.getElementsByTagName("return_msg") != null && document.getElementsByTagName("return_msg").getLength() > 0)
			return_msg = document.getElementsByTagName("return_msg").item(0).getTextContent();

		if (document.getElementsByTagName("appid") != null && document.getElementsByTagName("appid").getLength() > 0)
			appid = document.getElementsByTagName("appid").item(0).getTextContent();

		if (document.getElementsByTagName("mch_id") != null && document.getElementsByTagName("mch_id").getLength() > 0)
			mch_id = document.getElementsByTagName("mch_id").item(0).getTextContent();

		if (document.getElementsByTagName("device_info") != null && document.getElementsByTagName("device_info").getLength() > 0)
			device_info = document.getElementsByTagName("device_info").item(0).getTextContent();

		if (document.getElementsByTagName("nonce_str") != null && document.getElementsByTagName("nonce_str").getLength() > 0)
			nonce_str = document.getElementsByTagName("nonce_str").item(0).getTextContent();

		if (document.getElementsByTagName("sign") != null && document.getElementsByTagName("sign").getLength() > 0)
			sign = document.getElementsByTagName("sign").item(0).getTextContent();

		if (document.getElementsByTagName("result_code") != null && document.getElementsByTagName("result_code").getLength() > 0)
			result_code = document.getElementsByTagName("result_code").item(0).getTextContent();

		if (document.getElementsByTagName("err_code") != null && document.getElementsByTagName("err_code").getLength() > 0)
			err_code = document.getElementsByTagName("err_code").item(0).getTextContent();

		if (document.getElementsByTagName("err_code_des") != null && document.getElementsByTagName("err_code_des").getLength() > 0)
			err_code_des = document.getElementsByTagName("err_code_des").item(0).getTextContent();

		if (document.getElementsByTagName("openid") != null && document.getElementsByTagName("openid").getLength() > 0)
			openid = document.getElementsByTagName("openid").item(0).getTextContent();

		if (document.getElementsByTagName("is_subscribe") != null && document.getElementsByTagName("is_subscribe").getLength() > 0)
			is_subscribe = document.getElementsByTagName("is_subscribe").item(0).getTextContent();

		if (document.getElementsByTagName("trade_type") != null && document.getElementsByTagName("trade_type").getLength() > 0)
			trade_type = document.getElementsByTagName("trade_type").item(0).getTextContent();

		if (document.getElementsByTagName("bank_type") != null && document.getElementsByTagName("bank_type").getLength() > 0)
			bank_type = document.getElementsByTagName("bank_type").item(0).getTextContent();

		if (document.getElementsByTagName("total_fee") != null && document.getElementsByTagName("total_fee").getLength() > 0)
			total_fee = Integer.parseInt(document.getElementsByTagName("total_fee").item(0).getTextContent());

		if (document.getElementsByTagName("fee_type") != null && document.getElementsByTagName("fee_type").getLength() > 0)
			fee_type = document.getElementsByTagName("fee_type").item(0).getTextContent();

		if (document.getElementsByTagName("cash_fee") != null && document.getElementsByTagName("cash_fee").getLength() > 0)
			cash_fee = Integer.parseInt(document.getElementsByTagName("cash_fee").item(0).getTextContent());

		if (document.getElementsByTagName("cash_fee_type") != null && document.getElementsByTagName("cash_fee_type").getLength() > 0)
			fee_type = document.getElementsByTagName("cash_fee_type").item(0).getTextContent();

		if (document.getElementsByTagName("coupon_fee") != null && document.getElementsByTagName("coupon_fee").getLength() > 0)
			coupon_fee = Integer.parseInt(document.getElementsByTagName("coupon_fee").item(0).getTextContent());

		if (document.getElementsByTagName("coupon_count") != null && document.getElementsByTagName("coupon_count").getLength() > 0)
			coupon_count = Integer.parseInt(document.getElementsByTagName("coupon_count").item(0).getTextContent());

		if (document.getElementsByTagName("coupon_batch_id_$n") != null && document.getElementsByTagName("coupon_batch_id_$n").getLength() > 0)
			coupon_batch_id_$n = document.getElementsByTagName("coupon_batch_id_$n").item(0).getTextContent();

		if (document.getElementsByTagName("coupon_id_$n") != null && document.getElementsByTagName("coupon_id_$n").getLength() > 0)
			coupon_id_$n = document.getElementsByTagName("coupon_id_$n").item(0).getTextContent();

		if (document.getElementsByTagName("coupon_fee_$n") != null && document.getElementsByTagName("coupon_fee_$n").getLength() > 0)
			coupon_fee_$n = Integer.parseInt(document.getElementsByTagName("coupon_fee_$n").item(0).getTextContent());

		if (document.getElementsByTagName("transaction_id") != null && document.getElementsByTagName("transaction_id").getLength() > 0)
			transaction_id = document.getElementsByTagName("transaction_id").item(0).getTextContent();

		if (document.getElementsByTagName("out_trade_no") != null && document.getElementsByTagName("out_trade_no").getLength() > 0)
			out_trade_no = document.getElementsByTagName("out_trade_no").item(0).getTextContent();

		if (document.getElementsByTagName("attach") != null && document.getElementsByTagName("attach").getLength() > 0)
			attach = document.getElementsByTagName("attach").item(0).getTextContent();

		if (document.getElementsByTagName("time_end") != null && document.getElementsByTagName("time_end").getLength() > 0)
			time_end = document.getElementsByTagName("time_end").item(0).getTextContent();
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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public int getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(int cash_fee) {
		this.cash_fee = cash_fee;
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public int getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(int coupon_fee) {
		this.coupon_fee = coupon_fee;
	}

	public int getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(int coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getCoupon_batch_id_$n() {
		return coupon_batch_id_$n;
	}

	public void setCoupon_batch_id_$n(String coupon_batch_id_$n) {
		this.coupon_batch_id_$n = coupon_batch_id_$n;
	}

	public String getCoupon_id_$n() {
		return coupon_id_$n;
	}

	public void setCoupon_id_$n(String coupon_id_$n) {
		this.coupon_id_$n = coupon_id_$n;
	}

	public int getCoupon_fee_$n() {
		return coupon_fee_$n;
	}

	public void setCoupon_fee_$n(int coupon_fee_$n) {
		this.coupon_fee_$n = coupon_fee_$n;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
}