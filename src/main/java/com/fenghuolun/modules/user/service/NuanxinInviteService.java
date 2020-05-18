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
import com.fenghuolun.modules.user.entity.NuanxinInvite;
import com.fenghuolun.modules.user.entity.NuanxinUser;
import com.fenghuolun.modules.utils.StringUtil;
import com.fenghuolun.modules.user.dao.NuanxinInviteDao;
import com.fenghuolun.modules.user.dao.NuanxinUserDao;

/**
 * nuanxin_inviteService
 * @author zhengxiaotai
 * @version 2020-05-18
 */
@Service
@Transactional(readOnly=true)
public class NuanxinInviteService extends CrudService<NuanxinInviteDao, NuanxinInvite> {
	
	@Autowired
	private NuanxinUserDao nuanxinUserDao;
	
	/**
	 * 获取单条数据
	 * @param nuanxinInvite
	 * @return
	 */
	@Override
	public NuanxinInvite get(NuanxinInvite nuanxinInvite) {
		return super.get(nuanxinInvite);
	}
	
	/**
	 * 查询分页数据
	 * @param nuanxinInvite 查询条件
	 * @param nuanxinInvite.page 分页对象
	 * @return
	 */
	@Override
	public Page<NuanxinInvite> findPage(NuanxinInvite nuanxinInvite) {
		return super.findPage(nuanxinInvite);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param nuanxinInvite
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(NuanxinInvite nuanxinInvite) {
		super.save(nuanxinInvite);
	}
	
	/**
	 * 更新状态
	 * @param nuanxinInvite
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(NuanxinInvite nuanxinInvite) {
		super.updateStatus(nuanxinInvite);
	}
	
	/**
	 * 删除数据
	 * @param nuanxinInvite
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(NuanxinInvite nuanxinInvite) {
		super.delete(nuanxinInvite);
	}
	
	@Transactional(readOnly=false)
	public Map<String, Object> completeInvite(String inviteUserId, String invitedUserId) {
		Map<String, Object> result = new HashMap<>();
		NuanxinUser user = new NuanxinUser();
		user.setUserId(inviteUserId);
		user = nuanxinUserDao.get(user);
		if (user == null) {
			
		}
		user.setUserId(invitedUserId);
		user = nuanxinUserDao.get(user);
		if (user == null) {
			result.put("success", false);
			result.put("msg", "未找到用户");
			return result;
		}
		else {
			if (new Date().getTime() - user.getRegistTime().getTime() > 60000) {
				System.out.println(new Date().getTime() - user.getRegistTime().getTime());
				result.put("success", false);
				result.put("msg", "您已经是老用户了哦~");
				return result;
			}
		}
		NuanxinInvite invite = new NuanxinInvite();
		invite.setInviteUserId(inviteUserId);
		invite.setInvitedUserId(invitedUserId);
		if (dao.findCount(invite) > 0) {
			result.put("success", false);
			result.put("msg", "您已经接受过邀请了哦~");
			return result;
		}
		else {
			invite = new NuanxinInvite();
			invite.setInviteId("IVT" + System.currentTimeMillis() + StringUtil.randomStringNumberUpperCase(4));
			invite.setInviteUserId(inviteUserId);
			invite.setInvitedUserId(invitedUserId);
			invite.setInviteTime(new Date());
			dao.insert(invite);
			result.put("success", true);
			result.put("msg", "成功接受邀请~");
		}
		return result;
	}
}