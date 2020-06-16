/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.sys.entity.Config;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.utils.WechatUtil;
import com.fenghuolun.modules.utils.entity.AccessTokenResponse;
import com.fenghuolun.modules.utils.entity.JSCode2SessionResponse;
import com.fenghuolun.modules.order.dao.NuanxinCouponDao;
import com.fenghuolun.modules.order.dao.NuanxinOrderDao;
import com.fenghuolun.modules.order.entity.NuanxinCoupon;
import com.fenghuolun.modules.order.entity.NuanxinOrder;
import com.fenghuolun.modules.system.dao.NuanxinConfigDao;
import com.fenghuolun.modules.user.dao.NuanxinUserDao;

/**
 * nuanxin_userService
 * @author zhengxiaotai
 * @version 2020-03-09
 */
@Service
@Transactional(readOnly=true)
public class NuanxinUserService extends CrudService<NuanxinUserDao, NuanxinUser> {
	
	@Autowired
	private NuanxinConfigDao nuanxinConfigDao;
	@Autowired
	private NuanxinOrderDao nuanxinOrderDao;
	@Autowired
	private NuanxinCouponDao nuanxinCouponDao;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 获取单条数据
	 * @param nuanxinUser
	 * @return
	 */
	@Override
	public NuanxinUser get(NuanxinUser nuanxinUser) {
		return super.get(nuanxinUser);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinUser 查询条件
	 * @param nuanxinUser.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinUser> findPage(NuanxinUser nuanxinUser) {
		return super.findPage(nuanxinUser);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinUser nuanxinUser) {
		super.save(nuanxinUser);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinUser nuanxinUser) {
		super.updateStatus(nuanxinUser);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinUser
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinUser nuanxinUser) {
		super.delete(nuanxinUser);
	}

	@Transactional(readOnly=false)
	public Map<String, Object> login(String code, String userName, String image) {
		Map<String, Object> result = new HashMap<>();
		String appId = nuanxinConfigDao.getByConfigKey("nx.wechat.appId").getConfigValue();
		String appSecret = nuanxinConfigDao.getByConfigKey("nx.wechat.appSecret").getConfigValue();
		JSCode2SessionResponse loginResponse = WechatUtil.jsCode2Session(appId, appSecret, code);
		String wxOpenid = loginResponse.getOpenid();
		if (loginResponse.getErrcode() != null || wxOpenid == null) {
			result.put("success", false);
			result.put("msg", "登录失败");
			return result;
		}
		NuanxinUser user = dao.getByWxOpenId(wxOpenid);
		if (user == null) {
			user = new NuanxinUser();
			user.setUserId(StringUtil.randomStringNumberUpperCase(32));
			user.setUserName(userName);
			user.setUserStatus(1);
			user.setImage(image);
			user.setRegistTime(new Date());
			user.setWxOpenid(loginResponse.getOpenid());
			user.setWxSessionKey(loginResponse.getSession_key());
			dao.insert(user);
		}
		else {
			user.setWxOpenid(loginResponse.getOpenid());
			user.setWxSessionKey(loginResponse.getSession_key());
			dao.update(user);
		}
		result.put("success", true);
		result.put("msg", "登录成功");
		result.put("userId", user.getUserId());
		return result;
	}
	
	public Map<String, Object> getUserInfo(String userId) {
		Map<String, Object> result = new HashMap<>();
		NuanxinUser user = new NuanxinUser();
		user.setUserId(userId);
		user = dao.get(user);
		if (user == null) {
			result.put("success", false);
			result.put("msg", "未找到用户");
		}
		else {
			NuanxinOrder order = new NuanxinOrder();
			order.setUserId(userId);
			long orderCount = nuanxinOrderDao.findCount(order);
			NuanxinCoupon coupon = new NuanxinCoupon();
			coupon.setUserId(userId);
			coupon.setCouponStatus(1);
			long couponCount = nuanxinCouponDao.findCount(coupon);
			result.put("success", true);
			result.put("msg", "查询成功");
			result.put("userInfo", user);
			result.put("coupon", couponCount);
			result.put("order", orderCount);
		}
		return result;
	}

	@Transactional(readOnly=false)
	public Map<String, Object> updateUserInfo(String userId, String account, String password, String mobile) {
		Map<String, Object> result = new HashMap<>();
		NuanxinUser user = new NuanxinUser();
		user.setUserId(userId);
		user = dao.get(user);
		if (user == null) {
			result.put("success", false);
			result.put("msg", "未找到用户");
		}
		else {
			user.setGameAccount(account);
			user.setGamePassword(password);
			user.setMobile(mobile);
			dao.updateAccountInfo(user);
			result.put("success", true);
			result.put("msg", "更新成功");
		}
		return result;
	}
	
	@Transactional(readOnly=false)
	public Map<String, Object> getPoster(String userId, String page, String width) {
		Map<String, Object> result = new HashMap<>();
		String accessToken = "";
        try {
        	// 获取参数表存储的AccessToken，如果过期需要重新调用接口获取
    		Config config = nuanxinConfigDao.getByConfigKey("nx.wechat.accessToken");
    		if (config.getRemarks() == null || config.getRemarks().equals("") || format.parse(config.getRemarks()).before(new Date())) {
    			String appId = nuanxinConfigDao.getByConfigKey("nx.wechat.appId").getConfigValue();
    			String appSecret = nuanxinConfigDao.getByConfigKey("nx.wechat.appSecret").getConfigValue();
    			AccessTokenResponse response = WechatUtil.getAccessToken(appId, appSecret);
    			Calendar calendar = Calendar.getInstance();
    			calendar.add(Calendar.MINUTE, Integer.parseInt(response.getExpires_in()));
    			config.setConfigValue(response.getAccess_token());
    			config.setRemarks(format.format(calendar.getTime()));
    			nuanxinConfigDao.update(config);
    		}
    		accessToken = config.getConfigValue();
    		
    		String imgName = new Date().getTime() + ".jpg";
            String destination = "/home/warcraft/WEB-INF/classes/static/img/share/" + imgName;
            WechatUtil.getPoster(accessToken, userId, page, width, destination);
//            WechatUtil.getPoster(accessToken, userId, page, width, "D:/"+ new Date().getTime() + ".jpg");
            
    		result.put("success", true);
    		result.put("msg", "生成成功");
    		result.put("poster", "warcraft/static/img/share/" + imgName);
    		result.put("coverImage", "warcraft/static/img/share.jpg");
    		return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "生成失败");
			return result;
		}
	}
}