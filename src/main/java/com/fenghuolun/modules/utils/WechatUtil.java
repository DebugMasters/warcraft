package com.fenghuolun.modules.utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuolun.modules.order.entity.NuanxinOrder;
import com.fenghuolun.modules.order.entity.NuanxinWechatOrder;
import com.fenghuolun.modules.order.service.NuanxinOrderService;
import com.fenghuolun.modules.order.service.NuanxinWechatOrderService;
import com.fenghuolun.modules.system.service.NuanxinConfigService;
import com.fenghuolun.modules.utils.entity.AccessTokenResponse;
import com.fenghuolun.modules.utils.entity.JSCode2SessionResponse;
import com.fenghuolun.modules.utils.entity.MediaInfo;
import com.fenghuolun.modules.utils.entity.OrderInformResponse;
import com.fenghuolun.modules.utils.entity.UnifiedOrderRequest;
import com.fenghuolun.modules.utils.entity.UnifiedOrderResponse;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

@Component
public class WechatUtil {

	@Autowired
	private NuanxinConfigService nuanxinConfigService;
	@Autowired
	private NuanxinOrderService nuanxinOrderService;
	@Autowired
	private NuanxinWechatOrderService nuanxinWechatOrderService;

	private static NuanxinConfigService configService;
	private static NuanxinOrderService orderService;
	private static NuanxinWechatOrderService wechatOrderService;

	public static String JSCODE_2_SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	public static String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
	public static String BATCH_GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	public static String POSTER_URL = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";

	private static SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	@PostConstruct
	public void init() {
		configService = nuanxinConfigService;
		orderService = nuanxinOrderService;
		wechatOrderService = nuanxinWechatOrderService;
	}
	
	public static JSCode2SessionResponse jsCode2Session(String appId, String appSecret, String code) {
		String url = JSCODE_2_SESSION_URL.replace("APPID", appId).replace("SECRET", appSecret).replaceAll("JSCODE", code);
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = connection.getInputStream();
				byte[] bytes = new byte[1024];
				int i = 0;
				StringBuilder sb = new StringBuilder();
				while ((i = in.read(bytes)) != -1) {
					sb.append(new String(bytes, 0, i, "utf-8"));
				}
				in.close();
				JSCode2SessionResponse response = new Gson().fromJson(sb.toString(), JSCode2SessionResponse.class);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Map<String, Object> unifiedOrder(String openId, int money, String ip, String attach) {
		Map<String, Object> result = new HashMap<>();
		UnifiedOrderRequest request = new UnifiedOrderRequest();
		String outTradeNo = format.format(new Date()) + StringUtil.randomStringNumberUpperCase(7);
		request.setAppid(configService.getByConfigKey("nx.wechat.appId").getConfigValue());
		request.setMch_id(configService.getByConfigKey("nx.wechat.mchId").getConfigValue());
		request.setNonce_str(StringUtil.randomStringNumberUpperCase(30));
		request.setBody("烽火信息平台商品发布");
		request.setOpenid(openId);
		request.setOut_trade_no(outTradeNo);
		request.setTotal_fee(money * 100);
		request.setSpbill_create_ip(ip);
		request.setNotify_url(configService.getByConfigKey("nx.wechat.notifyUrl").getConfigValue());
		request.setTrade_type("JSAPI");
		request.setAttach(attach);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("appid", request.getAppid());
		param.put("mch_id", request.getMch_id());
		param.put("nonce_str", request.getNonce_str());
		param.put("body", request.getBody());
		param.put("openid", openId);
		param.put("out_trade_no", outTradeNo);
		param.put("total_fee", money * 100);
		param.put("spbill_create_ip", ip);
		param.put("notify_url", request.getNotify_url());
		param.put("trade_type", request.getTrade_type());
		param.put("attach", attach);
		request.setSign(getSign(param));
		
		XStream xStream = new XStream();
		xStream.alias("xml", UnifiedOrderRequest.class);
		String xmlData = xStream.toXML(request);
		xmlData = xmlData.replaceAll("__", "_");
		System.out.println(xmlData);
		try {
			URL url = new URL(UNIFIED_ORDER_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write(xmlData);
			writer.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String temp = "";
			StringBuffer buffer = new StringBuffer();
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			System.out.println(buffer);
			xStream = new XStream();
			xStream.alias("xml", UnifiedOrderResponse.class);
			UnifiedOrderResponse response = (UnifiedOrderResponse) xStream.fromXML(buffer.toString());
			System.out.println(response);
			if (!response.getReturn_code().equals("SUCCESS")) {
				return result;
			}
			
			Map<String, Object> again = new HashMap<String, Object>();
			again.put("appId", request.getAppid());
			again.put("timeStamp", System.currentTimeMillis() / 1000);
			again.put("nonceStr", response.getNonce_str());
			again.put("package", "prepay_id=" + response.getPrepay_id());
			again.put("signType", "MD5");
			String sign = getSign(again);
			again.put("sign", sign);
			return again;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}
	
	public static String getSign(Map<String, Object> map) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() != "") {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + configService.getByConfigKey("nx.wechat.key").getConfigValue();
		result = MD5.MD5Encode(result).toUpperCase();
		System.out.println(result);
		return result;
	}
	
	public static void payCallback(HttpServletRequest request) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			sb.append(line).append("\n");
		}
		String result = sb.toString();
		System.out.println(result);
		
		XStream xStream = new XStream();
		xStream.alias("xml", OrderInformResponse.class);
		OrderInformResponse response = (OrderInformResponse) xStream.fromXML(result);
		NuanxinWechatOrder wechatOrder = new NuanxinWechatOrder();
		wechatOrder.setReturnCode(response.getReturn_code());
		wechatOrder.setReturnMsg(response.getReturn_msg());
		wechatOrder.setData(result);
		if ("SUCCESS".equals(response.getReturn_code())) {
			// 返回成功，支付记录表新增记录
			String[] attach = response.getAttach().split("_");
			wechatOrder.setUserId(attach[0]);
			wechatOrder.setOrderId(attach[1]);
			if (!"SUCCESS".equals(response.getResult_code())) {
//				// 支付不成功，补全错误信息
				wechatOrder.setErrCode(response.getErr_code());
				wechatOrder.setErrCodeDes(response.getErr_code_des());
			}
			else {
				// 支付成功，补全支付信息
				wechatOrder.setOutTradeNo(response.getOut_trade_no());
				wechatOrder.setTotalFee(response.getTotal_fee());
				
				NuanxinOrder order = new NuanxinOrder();
				order.setOrderId(wechatOrder.getOrderId());
				order = orderService.get(order);
				if (order != null) {
					order.setOrderStatus(1);
					orderService.update(order);
				}
			}
		}
		else {
			wechatOrder.setErrCode(response.getErr_code());
			wechatOrder.setErrCodeDes(response.getErr_code_des());
		}
		wechatOrderService.insert(wechatOrder);
	}
	
	public static AccessTokenResponse getAccessToken(String appId, String appSecret) {
		String url = TOKEN_URL.replace("APPID", appId).replace("SECRET", appSecret);
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = connection.getInputStream();
				byte[] bytes = new byte[1024];
				int i = 0;
				StringBuilder sb = new StringBuilder();
				while ((i = in.read(bytes)) != -1) {
					sb.append(new String(bytes, 0, i, "utf-8"));
				}
				in.close();
				AccessTokenResponse response = new Gson().fromJson(sb.toString(), AccessTokenResponse.class);
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getPoster(String accessToken, String scene, String page, String width, String destination) {
		String url = POSTER_URL.replace("ACCESS_TOKEN", accessToken);
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
            connection.setInstanceFollowRedirects(false);
            connection.setDoOutput(true);
            connection.connect();
            OutputStream outputStream = connection.getOutputStream();
            String data = "{\"scene\":\"" + scene + "\",\"page\":\"" + page + "\",\"width\":" + width + "}";
            outputStream.write(data.getBytes());
            outputStream.flush();
            outputStream.close();
            
            InputStream inputStream = connection.getInputStream();
            byte[] res = new byte[1024];
            OutputStream image = new FileOutputStream(destination);
            int length;
			while ((length = inputStream.read(res)) != -1) {
				image.write(res, 0, length);
			}
            image.close();
            inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static MediaInfo batchGetMaterial(String accessToken) {
		String url = BATCH_GET_MATERIAL_URL.replace("ACCESS_TOKEN", accessToken);
		try {
			URL u = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) u.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write("{\"type\":\"news\", \"offset\":0, \"count\":20}");
			writer.close();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String temp = "";
			StringBuffer buffer = new StringBuffer();
			while ((temp = reader.readLine()) != null) {
				buffer.append(temp);
				buffer.append("\n");
			}
			System.out.println(buffer);
			MediaInfo mediaInfo = new Gson().fromJson(buffer.toString(), MediaInfo.class);
			return mediaInfo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
