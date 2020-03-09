/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.fenghuolun.modules.user.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.utils.WechatUtil;
import com.fenghuolun.modules.utils.entity.JSCode2SessionResponse;
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
}